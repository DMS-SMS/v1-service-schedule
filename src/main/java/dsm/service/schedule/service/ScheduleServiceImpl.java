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

    private final GetTimeTableUseCase getTimeTableUseCase;

    private final ScheduleMapper scheduleMapper;
    private final TimeTableMapper timeTableMapper;

    @Override
    public GetScheduleResponse getScheduleService(GetScheduleRequest request) {
        Iterable<Schedule> schedules = getScheduleUseCase.run();
        GetScheduleResponse.Builder response = scheduleMapper.getScheduleMapper(schedules);
        return response.build();
    }

    @Override
    public GetTimeTableResponse getTimeTableService(GetTimeTableRequest request) {
        TimeTable timetable= getTimeTableUseCase.run(request.getUuid(), request.getWeekNumber());
        return timeTableMapper.getScheduleMapper(timetable).build();
    }

    @Override
    public DefaultScheduleResponse createScheduleService(CreateScheduleRequest request) {
        createScheduleUseCase.run(
                request.getUuid(), request.getDetail(), request.getStartDate(), request.getEndDate());
        return DefaultScheduleResponse.newBuilder().setStatus(201).build();
    }

    @Override
    public DefaultScheduleResponse updateScheduleService(UpdateScheduleRequest request) {
        updateScheduleUseCase.run(
                request.getUuid(),
                request.getScheduleUUID(),
                request.getDetail(),
                request.getStartDate(),
                request.getEndDate()
        );
        return DefaultScheduleResponse.newBuilder().setStatus(200).build();
    }

    @Override
    public DefaultScheduleResponse deleteScheduleService(DeleteScheduleRequest request) {
        deleteScheduleUseCase.run(request.getUuid(), request.getScheduleUUID());
        return DefaultScheduleResponse.newBuilder().setStatus(200).build();
    }
}