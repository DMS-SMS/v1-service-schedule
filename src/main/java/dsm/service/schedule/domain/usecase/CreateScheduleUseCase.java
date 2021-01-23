package dsm.service.schedule.domain.usecase;

import java.time.LocalDate;

public interface CreateScheduleUseCase {
    public String execute(
            String teacherUuid, String detail, Long startTime, Long endTime
    );
}
