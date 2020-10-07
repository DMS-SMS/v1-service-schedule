package dsm.service.schedule.service;

import dsm.service.schedule.domain.usecase.GetScheduleUseCase;
import dsm.service.schedule.proto.CreateScheduleRequest;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import dsm.service.schedule.proto.GetScheduleResponse;
import dsm.service.schedule.proto.UpdateScheduleRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    public GetScheduleResponse getScheduleService();

    public DefaultScheduleResponse createScheduleService(CreateScheduleRequest request);

    public DefaultScheduleResponse updateScheduleService(UpdateScheduleRequest request);
}
