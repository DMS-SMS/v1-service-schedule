package dsm.service.schedule.domain.usecase;

import java.time.LocalDate;

public interface CreateScheduleUseCase {
    public void run(
            String teacherUuid, String detail, Long startTime, Long endTime
    );
}
