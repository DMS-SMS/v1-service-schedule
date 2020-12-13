package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.TimeTable;

public interface GetTimeTableUseCase {
    public TimeTable execute(String uuid, Integer year, Integer month, Integer day);
}
