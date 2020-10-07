package dsm.service.schedule.domain.usecase;

public interface UpdateScheduleUseCase {
    public void run(
            String teacherUuid, String scheduleUuid, String detail, Long startTime, Long endTime
    );
}
