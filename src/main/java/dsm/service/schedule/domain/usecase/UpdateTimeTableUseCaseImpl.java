package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.TimeTable;
import dsm.service.schedule.domain.repository.TimeTableRepository;
import dsm.service.schedule.infra.schoolApi.SchoolApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UpdateTimeTableUseCaseImpl {
    private final SchoolApiService schoolApiService;
    private final TimeTableRepository timeTableRepository;

    @Scheduled(fixedDelay = 2147483647)
    public void execute() {
        LocalDateTime now = LocalDateTime.now();
        String date = now.getMonthValue()+"/01/"+now.getYear();
        LocalDate LastLocalDateOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/dd/yyyy"))
                .with(TemporalAdjusters.lastDayOfMonth());
        int lastDayOfMonth = Integer.parseInt(String.valueOf(LastLocalDateOfMonth.getDayOfMonth()));

        for (int grade = 1; grade <= 3; grade++) {
            for (int group = 1; group <= 4; group++) {
                for (int day = 1; day <= lastDayOfMonth; day++ ) {
                    Map<Integer, String> timetableMap = schoolApiService.getTimeTable(
                            grade, group, now.getYear(), now.getMonthValue(), day);

                    TimeTable.TimeTableBuilder timeTableBuilder = TimeTable.builder()
                            .uuid(
                                    String.valueOf(now.getYear())+
                                    String.valueOf(now.getMonthValue())+
                                    String.valueOf(day)+
                                    String.valueOf(grade)+
                                    String.valueOf(group))
                            .day(
                                    String.valueOf(now.getYear())+
                                    String.valueOf(now.getMonthValue())+
                                    String.valueOf(day))
                            .targetGrade(grade)
                            .targetGroup(group);

                    for (Integer key : timetableMap.keySet()) {
                        timeTableBuilder = putPeriod(timeTableBuilder, key, timetableMap.get(key));
                    }

                    assert timeTableBuilder != null;
                    timeTableRepository.save(timeTableBuilder.build());
                }
            }
        }
        int nextYear = now.getYear();
        int nextMonth = now.getMonthValue();
        if (nextMonth == 12) {
            nextMonth = 1;
            nextYear += 1;
        }

        String nextDate = nextMonth+"/01/"+nextYear;
        LocalDate LastLocalDateOfNextMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/dd/yyyy"))
                .with(TemporalAdjusters.lastDayOfMonth());
        int lastDayOfNextMonth = Integer.parseInt(String.valueOf(LastLocalDateOfNextMonth.getDayOfMonth()));

        for (int grade = 1; grade <= 3; grade++) {
            for (int group = 1; group <= 4; group++) {
                for (int day = 1; day <= lastDayOfNextMonth; day++ ) {
                    Map<Integer, String> timetableMap = schoolApiService.getTimeTable(
                            grade, group, nextYear, nextMonth, day);

                    TimeTable.TimeTableBuilder timeTableBuilder = TimeTable.builder()
                            .uuid(
                                    String.valueOf(nextYear)+
                                            String.valueOf(nextMonth)+
                                            String.valueOf(day)+
                                            String.valueOf(grade)+
                                            String.valueOf(group))
                            .day(
                                    String.valueOf(nextYear)+
                                            String.valueOf(nextMonth)+
                                            String.valueOf(day))
                            .targetGrade(grade)
                            .targetGroup(group);

                    for (Integer key : timetableMap.keySet()) {
                        timeTableBuilder = putPeriod(timeTableBuilder, key, timetableMap.get(key));
                    }

                    assert timeTableBuilder != null;
                    timeTableRepository.save(timeTableBuilder.build());
                }
            }
        }
    }
    private TimeTable.TimeTableBuilder putPeriod(TimeTable.TimeTableBuilder timeTableBuilder, Integer time, String subject) {
        if (time == 1) {
            return timeTableBuilder.firstPeriod(subject);
        } else if (time == 2) {
            return timeTableBuilder.secondPeriod(subject);
        } else if (time == 3) {
            return timeTableBuilder.thirdPeriod(subject);
        } else if (time == 4) {
            return timeTableBuilder.fourthPeriod(subject);
        } else if (time == 5) {
            return timeTableBuilder.fifthPeriod(subject);
        } else if (time == 6) {
            return timeTableBuilder.sixthPeriod(subject);
        } else if (time == 7) {
            return timeTableBuilder.seventhPeriod(subject);
        }
        return null;
    }
}
