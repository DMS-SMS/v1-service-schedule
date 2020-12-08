package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

@Component
@RequiredArgsConstructor
public class GetScheduleUseCaseImpl implements GetScheduleUseCase{
    private final ScheduleRepository scheduleRepository;

    @Override
    public Iterable<Schedule> run(int year, int month) {
        String date = month+"/"+year;
        int lastDayOfMonth = Integer.parseInt(String.valueOf(LocalDate.parse(date, DateTimeFormatter.ofPattern("M/dd/yyyy"))
                .with(TemporalAdjusters.lastDayOfMonth())));

        return scheduleRepository.findAllByStartDateBetweenOrEndDateBetween(
                LocalDate.of(year, month, 1),
                LocalDate.of(year, month, lastDayOfMonth),
                LocalDate.of(year, month, 1),
                LocalDate.of(year, month, lastDayOfMonth)
                );
    }

}