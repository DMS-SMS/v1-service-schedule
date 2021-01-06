package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Timetable;
import dsm.service.schedule.proto.TimeTable;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class GetTimeTablesUseCaseImpl implements GetTimeTablesUseCase {
    private final GetTimeTableUseCase getTimeTableUseCase;

    @Override
    public List<Timetable> execute(String uuid, Integer year, Integer month, Integer day, Integer count) {
        return generateTimeTable(uuid, year, month, day, count);
    }

    private List<Timetable> generateTimeTable(String uuid, Integer year, Integer month, Integer day, Integer count) {
        List<Timetable> timetables = new ArrayList<Timetable>();
        LocalDate date = generateDate(year, month, day);

        for (int i = 0; i < count; i++) {
            timetables.add(getTimeTableUseCase.execute(uuid, date.getYear(), date.getMonth().getValue(), date.getDayOfMonth()));
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
}
