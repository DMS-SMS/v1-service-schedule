package dsm.service.schedule.core.domain.entity;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Schedule {
    String scheduleUuid;

    String detail;

    LocalDate startDate;

    LocalDate endDate;
}
