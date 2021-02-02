package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.Mapper;
import dsm.service.schedule.core.usecase.schedule.DeleteScheduleUseCase;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import org.springframework.stereotype.Component;

@Component
public class DeleteScheduleOutputMapper extends Mapper<DeleteScheduleUseCase.OutputValues, DefaultScheduleResponse> {
    @Override
    public DefaultScheduleResponse map(DeleteScheduleUseCase.OutputValues input) {
        return DefaultScheduleResponse.newBuilder()
                .setStatus(201)
                .setScheduleUUID(input.getSchedule().getScheduleUuid())
                .build();
    }
}
