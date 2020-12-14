package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Timetable;

public interface GetTimeTableUseCase {
    public Timetable execute(String uuid, Integer year, Integer month, Integer day);
}
