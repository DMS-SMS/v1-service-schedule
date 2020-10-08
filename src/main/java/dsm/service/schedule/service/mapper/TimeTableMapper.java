package dsm.service.schedule.service.mapper;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.entity.TimeTable;
import dsm.service.schedule.proto.GetScheduleResponse;
import dsm.service.schedule.proto.GetTimeTableResponse;
import org.springframework.stereotype.Component;

@Component
public class TimeTableMapper {
    public GetTimeTableResponse.Builder getScheduleMapper(
            TimeTable timeTable
    ) {
        GetTimeTableResponse.Builder response = GetTimeTableResponse.newBuilder();
        response.setStatus(200)
                .setTime1(timeTable.getFifthPeriod())
                .setTime2(timeTable.getSecondPeriod())
                .setTime3(timeTable.getThirdPeriod())
                .setTime4(timeTable.getFourthPeriod())
                .setTime5(timeTable.getFifthPeriod())
                .setTime6(timeTable.getSixthPeriod())
                .setTime7(timeTable.getSeventhPeriod());

        return response;
    }
}
