package dsm.service.schedule.service;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.usecase.CreateScheduleUseCase;
import dsm.service.schedule.domain.usecase.GetScheduleUseCase;
import dsm.service.schedule.domain.usecase.UpdateScheduleUseCase;
import dsm.service.schedule.proto.CreateScheduleRequest;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import dsm.service.schedule.proto.GetScheduleResponse;
import dsm.service.schedule.proto.UpdateScheduleRequest;
import dsm.service.schedule.service.mapper.ScheduleMapper;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final GetScheduleUseCase getScheduleUseCase;
    private final CreateScheduleUseCase createScheduleUseCase;
    private final UpdateScheduleUseCase updateScheduleUseCase;

    private final ScheduleMapper scheduleMapper;

    @Override
    public GetScheduleResponse getScheduleService() {
        Iterable<Schedule> schedules = getScheduleUseCase.run();
        GetScheduleResponse.Builder response = scheduleMapper.getScheduleMapper(schedules);
        return response.build();
    }

    @Override
    public DefaultScheduleResponse createScheduleService(CreateScheduleRequest request) {
        createScheduleUseCase.run(request.getUuid(), request.getDetail(), request.getStartDate(), request.getEndDate());
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
}