package dsm.service.schedule.data.api.school;

import dsm.service.schedule.data.db.jpa.model.TimetableModel;
import dsm.service.schedule.data.db.jpa.repository.JpaTimetableRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

@Component
@RequiredArgsConstructor
public class SchoolApiService {
    private final JpaTimetableRepository jpaTimetableRepository;

    @Value("${schedule.schoolApi.key}")
    private String key;

    @Scheduled(fixedDelay = 2147483647)
    void updateTimetable() {
        LocalDateTime now = LocalDateTime.now();
        String date = now.getMonthValue()+"/01/"+now.getYear();

        updateMonthlyTimetable(now.getYear(), now.getMonth().getValue(), getLastDayOfMonth(date));

        int nextYear = now.getYear();
        int nextMonth = now.getMonthValue() + 1;
        if (nextMonth == 13) {
            nextMonth = 1;
            nextYear += 1;
        }

        String nextDate = nextMonth+"/01/"+nextYear;

        updateMonthlyTimetable(nextYear, nextMonth, getLastDayOfMonth(nextDate));
    }

    private int getLastDayOfMonth(String date) {
        LocalDate LastLocalDateOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/dd/yyyy"))
                .with(TemporalAdjusters.lastDayOfMonth());
        return Integer.parseInt(String.valueOf(LastLocalDateOfMonth.getDayOfMonth()));
    }

    private void updateMonthlyTimetable(Integer year, Integer month, Integer lastDayOfMonth) {
        for (int grade = 1; grade <= 3; grade++) {
            for (int group = 1; group <= 4; group++) {
                for (int day = 1; day <= lastDayOfMonth; day++) {
                    TimetableModel timetableModel = getTimetable(grade, group, year, month, day);
                    assert timetableModel != null;
                    jpaTimetableRepository.save(timetableModel);
                }
            }
        }
    }

    private TimetableModel getTimetable(Integer grade, Integer group, Integer year, Integer month, Integer day) {
        TimetableModel.TimetableModelBuilder timetableModelBuilder = TimetableModel.builder();
        timetableModelBuilder
                .uuid(year.toString()+String.format("%02d", month)+String.format("%02d", day)+grade.toString()+group.toString())
                .targetGrade(grade)
                .targetGroup(group)
                .date(LocalDate.of(year, month, day));
        try {
            String json = Jsoup.connect(
                    "https://open.neis.go.kr/hub/hisTimetable?" +
                            "KEY="+key+
                            "&Type=json" +
                            "&ATPT_OFCDC_SC_CODE=G10" +
                            "&SD_SCHUL_CODE=7430310" +
                            "&GRADE="+grade.toString()+
                            "&CLASS_NM="+group.toString()+
                            "&ALL_TI_YMD="+year.toString()+String.format("%02d", month)+String.format("%02d", day))
                    .ignoreContentType(true).execute().body();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
            JSONArray hisTimetable = (JSONArray) jsonObject.get("hisTimetable");
            JSONObject timetable = (JSONObject) hisTimetable.get(1);
            JSONArray row = (JSONArray) timetable.get("row");

            return putSubjects(timetableModelBuilder, row).build();
        } catch (Exception e) {
            return timetableModelBuilder.build();
        }
    }

    private TimetableModel.TimetableModelBuilder putSubjects(TimetableModel.TimetableModelBuilder builder, JSONArray row) {
        for (Object table: row) {
            JSONObject jsonTable = (JSONObject) table;
            builder = pubSubject(builder, Integer.valueOf(jsonTable.get("PERIO").toString()), jsonTable.get("ITRT_CNTNT").toString());
        }
        return builder;
    }

    private TimetableModel.TimetableModelBuilder pubSubject(
            TimetableModel.TimetableModelBuilder builder, Integer perio, String subject) {
        switch (perio) {
            case 1: builder.firstSubject(subject);
            case 2: builder.secondSubject(subject);
            case 3: builder.thirdSubject(subject);
            case 4: builder.fourthSubject(subject);
            case 5: builder.fifthSubject(subject);
            case 6: builder.sixthSubject(subject);
            case 7: builder.seventhSubject(subject);
            default: return builder;
        }
    }
}
