package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.usecase.schedule.UpdateScheduleUseCase;
import dsm.service.schedule.proto.UpdateScheduleRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateScheduleInputMapper {
    public UpdateScheduleUseCase.InputValues map(UpdateScheduleRequest request) {
        return new UpdateScheduleUseCase.InputValues(
                request.getUuid(),
                request.getScheduleUUID(),
                request.getDetail(),
                request.getStartDate(),
                request.getEndDate()
        );
    }
}
