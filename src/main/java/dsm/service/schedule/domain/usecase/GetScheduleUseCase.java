package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Schedule;

import java.util.Optional;

public interface GetScheduleUseCase {
    public Iterable<Schedule> run(int year, int month);
}
