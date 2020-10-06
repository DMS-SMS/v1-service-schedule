package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetScheduleUseCaseImpl implements GetScheduleUseCase{
    private final ScheduleRepository scheduleRepository;

    @Override
    public Iterable<Schedule> run() {
        return scheduleRepository.findAll();
    }
}