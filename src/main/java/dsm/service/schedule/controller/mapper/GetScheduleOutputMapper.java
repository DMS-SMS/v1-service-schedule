package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.Mapper;
import dsm.service.schedule.core.domain.entity.Schedule;
import dsm.service.schedule.core.usecase.schedule.GetScheduleUseCase;
import dsm.service.schedule.proto.GetScheduleResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class GetScheduleOutputMapper extends Mapper<GetScheduleUseCase.OutputValues, GetScheduleResponse> {
    @Override
    public GetScheduleResponse map(GetScheduleUseCase.OutputValues input) {
        GetScheduleResponse.Builder builder = GetScheduleResponse.newBuilder();

        return mapItems(builder, input.getSchedules()).setStatus(200).build();
    }

    private GetScheduleResponse.Builder mapItems(GetScheduleResponse.Builder builder, List<Schedule> schedules) {
        for (Schedule schedule: schedules) {
            builder.addSchedule(
                    dsm.service.schedule.proto.Schedule.newBuilder()
                            .setScheduleUUID(schedule.getScheduleUuid())
                            .setDetail(schedule.getDetail())
                            .setStartDate(Timestamp.valueOf(LocalDateTime.of(schedule.getStartDate(), LocalTime.of(0, 0))).getTime())
                            .setEndDate(Timestamp.valueOf(LocalDateTime.of(schedule.getEndDate(), LocalTime.of(0, 0))).getTime())
                            .build()
            );
        }
        return builder;
    }
}
