package dsm.service.schedule.domain.usecase;

public interface DeleteScheduleUseCase {
    public void run(String teacherUuid, String scheduleUuid);
}
