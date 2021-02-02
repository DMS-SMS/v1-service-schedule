package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.usecase.schedule.DeleteScheduleUseCase;
import dsm.service.schedule.proto.DeleteScheduleRequest;
import org.springframework.stereotype.Component;

@Component
public class DeleteScheduleInputMapper {
    public DeleteScheduleUseCase.InputValues map(DeleteScheduleRequest request) {
        return new DeleteScheduleUseCase.InputValues(
            request.getUuid(), request.getScheduleUUID()
        );
    }
}
