package dsm.service.schedule.service.mapper;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.proto.GetScheduleResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
            .setStartDate(Timestamp.valueOf(LocalDateTime.of(schedule.getStartDate(), LocalTime.of(1, 1))).getTime())
            .setEndDate(Timestamp.valueOf(LocalDateTime.of(schedule.getEndDate(), LocalTime.of(1, 1))).getTime())
            .setDetail(schedule.getDetail())
            .build());
        }

        return response;
    }
}
