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
    public GetScheduleResponse getScheduleService(GetScheduleRequest request, String xRequestId, String spanContext) {
        Iterable<Schedule> schedules = getScheduleUseCase.run();
        GetScheduleResponse.Builder response = scheduleMapper.getScheduleMapper(schedules);
        return response.build();
    }

    @Override
    public GetTimeTableResponse getTimeTableService(GetTimeTableRequest request, String xRequestId, String spanContext) {
        TimeTable timetable= getTimeTableUseCase.run(request.getUuid());
        return timeTableMapper.getScheduleMapper(timetable).build();
    }

    @Override
    public DefaultScheduleResponse createScheduleService(CreateScheduleRequest request, String xRequestId, String spanContext) {
        createScheduleUseCase.run(request.getUuid(), request.getDetail(), request.getStartDate(), request.getEndDate());
        return DefaultScheduleResponse.newBuilder().setStatus(201).build();
    }

    @Override
    public DefaultScheduleResponse updateScheduleService(UpdateScheduleRequest request, String xRequestId, String spanContext) {
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
    public DefaultScheduleResponse deleteScheduleService(DeleteScheduleRequest request, String xRequestId, String spanContext) {
        deleteScheduleUseCase.run(request.getUuid(), request.getScheduleUUID());
        return DefaultScheduleResponse.newBuilder().setStatus(200).build();
    }
}