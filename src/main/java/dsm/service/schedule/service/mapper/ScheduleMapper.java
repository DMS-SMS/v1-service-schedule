package dsm.service.schedule.service.mapper;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.proto.GetScheduleResponse;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    public GetScheduleResponse.Builder getScheduleMapper(
            Iterable<Schedule> schedules
    ) {
        GetScheduleResponse.Builder response = GetScheduleResponse.newBuilder();
        for (Schedule schedule: schedules) {
            response.addSchedule(
                    dsm.service.schedule.proto.Schedule.newBuilder()
            .setScheduleUUID(schedule.getUuid())
            .setStartDate(schedule.getStartDate().toEpochDay())
            .setEndDate(schedule.getEndDate().toEpochDay())
            .setDetail(schedule.getDetail())
            .build());
        }

        return response;
    }
}
