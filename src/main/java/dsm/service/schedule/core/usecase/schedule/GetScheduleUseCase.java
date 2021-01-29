package dsm.service.schedule.core.usecase.schedule;

import dsm.service.schedule.core.domain.entity.Schedule;
import dsm.service.schedule.core.domain.repository.ScheduleRepository;
import dsm.service.schedule.core.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetScheduleUseCase extends UseCase<GetScheduleUseCase.InputValues, GetScheduleUseCase.OutputValues> {
    private final ScheduleRepository scheduleRepository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(scheduleRepository.findByCorrectDate(generateDate(input.year, input.month)));
    }

    private String generateDate(int year, int month) {
        return year+"-"+month;
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        int year;
        int month;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<Schedule> schedules;
    }
}
