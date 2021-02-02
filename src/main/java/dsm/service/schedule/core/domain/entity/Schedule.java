package dsm.service.schedule.core.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Schedule {
    String scheduleUuid;

    String detail;

    LocalDate startDate;

    LocalDate endDate;

    public Schedule updateSchedule(String detail, LocalDate startDate, LocalDate endDate) {
        this.detail = detail;
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }
}
