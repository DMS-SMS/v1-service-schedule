package dsm.service.schedule.service;

import dsm.service.schedule.domain.usecase.GetScheduleUseCase;
import dsm.service.schedule.domain.usecase.GetTimeTableUseCase;
import dsm.service.schedule.proto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    public GetScheduleResponse getScheduleService(GetScheduleRequest request);

    public GetTimeTableResponse getTimeTableService(GetTimeTableRequest request);

    public GetTimeTablesResponse getTimeTablesService(GetTimeTablesRequest request);

    public DefaultScheduleResponse createScheduleService(CreateScheduleRequest request);

    public DefaultScheduleResponse updateScheduleService(UpdateScheduleRequest request);

    public DefaultScheduleResponse deleteScheduleService(DeleteScheduleRequest request);
}
