package dsm.service.schedule.service;

import dsm.service.schedule.domain.usecase.GetScheduleUseCase;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import dsm.service.schedule.proto.GetScheduleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    public GetScheduleResponse getScheduleService();
}
