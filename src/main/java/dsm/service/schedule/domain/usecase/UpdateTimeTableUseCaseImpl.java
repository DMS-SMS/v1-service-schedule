package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.TimeTable;
import dsm.service.schedule.domain.repository.TimeTableRepository;
import dsm.service.schedule.infra.schoolApi.SchoolApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UpdateTimeTableUseCaseImpl {
    private final SchoolApiService schoolApiService;
    private final TimeTableRepository timeTableRepository;

    @Scheduled(fixedDelay = 259200000)
    public void execute() {
        Map<Integer, String> timetableMap = schoolApiService.getTimeTable(2, 1, 2020, 11, 14);
        TimeTable.TimeTableBuilder timeTableBuilder = TimeTable.builder().uuid("2020121421").day("20201214").targetGrade(2).targetGroup(1);
        for (Integer key: timetableMap.keySet()) {
            timeTableBuilder = putPeriod(timeTableBuilder, key, timetableMap.get(key));
        }
        assert timeTableBuilder != null;
        timeTableRepository.save(timeTableBuilder.build());
    }
    // id 추가 및 스케줄링 안됨. 및 트레이싱 추가
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
