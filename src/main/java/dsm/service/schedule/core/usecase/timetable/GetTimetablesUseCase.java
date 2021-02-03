package dsm.service.schedule.core.usecase.timetable;

import dsm.service.schedule.core.domain.entity.Timetable;
import dsm.service.schedule.core.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetTimetablesUseCase extends UseCase<GetTimetablesUseCase.InputValues, GetTimetablesUseCase.OutputValues>{
    private final GetTimetableUseCase getTimetableUseCase;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(generateTimeTable(input.accountUuid, input.year, input.month, input.day, input.count));
    }

    private List<Timetable> generateTimeTable(String uuid, Integer year, Integer month, Integer day, Integer count) {
        List<Timetable> timetables = new ArrayList<Timetable>();
        LocalDate date = generateDate(year, month, day);

        for (int i = 0; i < count; i++) {
            timetables.add(
                    getTimetableUseCase.execute(new GetTimetableUseCase.InputValues(
                            uuid, date.getYear(), date.getMonth().getValue(), date.getDayOfMonth()
                    )).getTimetable()
            );
            date = getNextDay(date);
        }
        return timetables;
    }

    private LocalDate generateDate(Integer year, Integer month, Integer day) {
        return LocalDate.of(year, month, day);
    }

    private LocalDate getNextDay(LocalDate date) {
        return date.plusDays(1);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        String accountUuid;
        Integer year;
        Integer month;
        Integer day;
        Integer count;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<Timetable> timetables;
    }
}
