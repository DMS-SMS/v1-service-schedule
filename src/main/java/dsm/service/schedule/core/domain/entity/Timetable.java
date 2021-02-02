package dsm.service.schedule.core.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Getter
@Builder
public class Timetable {
    String timetableUuid;

    Integer targetGrade;

    Integer targetGroup;

    LocalDate date;

    String firstSubject;

    String secondSubject;

    String thirdSubject;

    String fourthSubject;

    String fifthSubject;

    String sixthSubject;

    String seventhSubject;
}
