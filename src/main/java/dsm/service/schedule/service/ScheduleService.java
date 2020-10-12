package dsm.service.schedule.service;

import dsm.service.schedule.domain.usecase.GetScheduleUseCase;
import dsm.service.schedule.domain.usecase.GetTimeTableUseCase;
import dsm.service.schedule.proto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    public GetScheduleResponse getScheduleService(GetScheduleRequest request, String xRequestId, String spanContext);

    public GetTimeTableResponse getTimeTableService(GetTimeTableRequest request, String xRequestId, String spanContext);

    public DefaultScheduleResponse createScheduleService(CreateScheduleRequest request, String xRequestId, String spanContext);

    public DefaultScheduleResponse updateScheduleService(UpdateScheduleRequest request, String xRequestId, String spanContext);

    public DefaultScheduleResponse deleteScheduleService(DeleteScheduleRequest request, String xRequestId, String spanContext);
}
