package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Timetable;
import dsm.service.schedule.proto.TimeTable;

import java.util.List;

public interface GetTimeTablesUseCase {
    public List<Timetable> execute(String uuid, Integer year, Integer month, Integer day, Integer count);
}
