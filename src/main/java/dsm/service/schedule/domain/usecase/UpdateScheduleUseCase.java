package dsm.service.schedule.domain.usecase;

public interface UpdateScheduleUseCase {
    public void execute(
            String teacherUuid, String scheduleUuid, String detail, Long startTime, Long endTime
    );
}
