package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.usecase.schedule.CreateScheduleUseCase;
import dsm.service.schedule.proto.CreateScheduleRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleInputMapper {
    public CreateScheduleUseCase.InputValues map(CreateScheduleRequest request) {
        return new CreateScheduleUseCase.InputValues(
                request.getUuid(),
                request.getDetail(),
                request.getStartDate(),
                request.getEndDate()
        );
    }
}
