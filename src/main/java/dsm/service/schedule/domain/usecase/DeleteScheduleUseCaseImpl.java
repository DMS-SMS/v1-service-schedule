package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteScheduleUseCaseImpl implements DeleteScheduleUseCase {
    private final ScheduleRepository scheduleRepository;

    @Override
    public void run(String teacherUuid, String scheduleUuid) {
        scheduleRepository.findById(scheduleUuid).ifPresent(
                scheduleRepository::delete
        );
    }
}
