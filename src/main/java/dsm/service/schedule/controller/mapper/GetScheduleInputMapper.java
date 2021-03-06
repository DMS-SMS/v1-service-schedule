package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.usecase.schedule.GetScheduleUseCase;
import dsm.service.schedule.proto.GetScheduleRequest;
import org.springframework.stereotype.Component;

@Component
public class GetScheduleInputMapper {
    public GetScheduleUseCase.InputValues map(GetScheduleRequest request) {
        return new GetScheduleUseCase.InputValues(
                request.getYear(),
                request.getMonth()
        );
    }
}
