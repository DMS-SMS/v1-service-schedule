package dsm.service.schedule.service;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.entity.TimeTable;
import dsm.service.schedule.domain.usecase.*;
import dsm.service.schedule.proto.*;
import dsm.service.schedule.service.mapper.ScheduleMapper;
import dsm.service.schedule.service.mapper.TimeTableMapper;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.sql.Time;


@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final GetScheduleUseCase getScheduleUseCase;
    private final CreateScheduleUseCase createScheduleUseCase;
    private final UpdateScheduleUseCase updateScheduleUseCase;
    private final DeleteScheduleUseCase deleteScheduleUseCase;

    private final UpdateTimeTableUseCaseImpl updateTimeTableUseCase;
    private final GetTimeTableUseCase getTimeTableUseCase;

    private final ScheduleMapper scheduleMapper;
    private final TimeTableMapper timeTableMapper;

    @Override
    public GetScheduleResponse getScheduleService(GetScheduleRequest request) {
        Iterable<Schedule> schedules = getScheduleUseCase.execute(request.getYear(), request.getMonth());
        GetScheduleResponse.Builder response = scheduleMapper.getScheduleMapper(schedules);
        return response.setStatus(200).build();
    }

    @Override
    public GetTimeTableResponse getTimeTableService(GetTimeTableRequest request) {
        updateTimeTableUseCase.execute();
        TimeTable timetable= getTimeTableUseCase.execute(request.getUuid(), request.getWeekNumber());
        return timeTableMapper.getTimeTableMapper(timetable).setStatus(200).build();
    }

    @Override
    public DefaultScheduleResponse createScheduleService(CreateScheduleRequest request) {
        String schedule_uuid = createScheduleUseCase.execute(
                request.getUuid(), request.getDetail(), request.getStartDate(), request.getEndDate());
        return DefaultScheduleResponse.newBuilder().setScheduleUUID(schedule_uuid).setStatus(201).build();
    }

    @Override
    public DefaultScheduleResponse updateScheduleService(UpdateScheduleRequest request) {
        updateScheduleUseCase.execute(
                request.getUuid(),
                request.getScheduleUUID(),
                request.getDetail(),
                request.getStartDate(),
                request.getEndDate()
        );
        return DefaultScheduleResponse.newBuilder().setScheduleUUID(request.getScheduleUUID()).setStatus(200).build();
    }

    @Override
    public DefaultScheduleResponse deleteScheduleService(DeleteScheduleRequest request) {
        deleteScheduleUseCase.execute(request.getUuid(), request.getScheduleUUID());
        return DefaultScheduleResponse.newBuilder().setScheduleUUID(request.getScheduleUUID()).setStatus(200).build();
    }
}