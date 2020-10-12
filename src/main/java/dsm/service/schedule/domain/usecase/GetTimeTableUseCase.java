package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.TimeTable;

public interface GetTimeTableUseCase {
    public TimeTable run(String uuid, String xRequestId);
}
