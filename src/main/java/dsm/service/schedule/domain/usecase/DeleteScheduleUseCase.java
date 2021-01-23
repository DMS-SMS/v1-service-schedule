package dsm.service.schedule.domain.usecase;

public interface DeleteScheduleUseCase {
    public void execute(String teacherUuid, String scheduleUuid);
}
