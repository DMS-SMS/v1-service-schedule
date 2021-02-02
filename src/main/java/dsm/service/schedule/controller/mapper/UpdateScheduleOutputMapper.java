package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.Mapper;
import dsm.service.schedule.core.usecase.schedule.UpdateScheduleUseCase;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdateScheduleOutputMapper extends Mapper<UpdateScheduleUseCase.OutputValues, DefaultScheduleResponse> {
    @Override
    public DefaultScheduleResponse map(UpdateScheduleUseCase.OutputValues input) {
        return DefaultScheduleResponse.newBuilder()
                .setStatus(201)
                .setScheduleUUID(input.getSchedule().getScheduleUuid())
                .build();
    }
}
