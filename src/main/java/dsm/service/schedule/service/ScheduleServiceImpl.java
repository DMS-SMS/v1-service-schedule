package dsm.service.schedule.service;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.usecase.GetScheduleUseCase;
import dsm.service.schedule.proto.GetScheduleResponse;
import dsm.service.schedule.service.mapper.ScheduleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final GetScheduleUseCase getScheduleUseCase;

    private final ScheduleMapper scheduleMapper;

    @Override
    public GetScheduleResponse getScheduleService() {
        Iterable<Schedule> schedules = getScheduleUseCase.run();
        GetScheduleResponse.Builder response = scheduleMapper.getScheduleMapper(schedules);
        return response.build();
    }
}