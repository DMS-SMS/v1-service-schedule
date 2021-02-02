package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.Mapper;
import dsm.service.schedule.core.usecase.schedule.CreateScheduleUseCase;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleOutputMapper extends Mapper<CreateScheduleUseCase.OutputValues, DefaultScheduleResponse> {
    @Override
    public DefaultScheduleResponse map(CreateScheduleUseCase.OutputValues input) {
        return DefaultScheduleResponse.newBuilder()
                .setStatus(201)
                .setScheduleUUID(input.getSchedule().getScheduleUuid())
                .build();
    }
}
