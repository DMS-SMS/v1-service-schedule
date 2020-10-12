package dsm.service.schedule.domain.usecase;

public interface UpdateScheduleUseCase {
    public void run(
            String teacherUuid, String xRequestId, String scheduleUuid, String detail, Long startTime, Long endTime
    );
}
