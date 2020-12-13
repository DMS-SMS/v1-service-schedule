package dsm.service.schedule.service.mapper;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.entity.TimeTable;
import dsm.service.schedule.proto.GetScheduleResponse;
import dsm.service.schedule.proto.GetTimeTableResponse;
import org.springframework.stereotype.Component;

@Component
public class TimeTableMapper {
    public GetTimeTableResponse.Builder getTimeTableMapper(
            TimeTable timeTable
    ) {
        GetTimeTableResponse.Builder response = GetTimeTableResponse.newBuilder();
        if (timeTable.getFirstPeriod() != null) response.setTime1(timeTable.getFirstPeriod());
        if (timeTable.getSecondPeriod() != null) response.setTime2(timeTable.getSecondPeriod());
        if (timeTable.getThirdPeriod() != null) response.setTime3(timeTable.getThirdPeriod());
        if (timeTable.getFourthPeriod() != null) response.setTime4(timeTable.getFourthPeriod());
        if (timeTable.getFifthPeriod() != null) response.setTime5(timeTable.getFifthPeriod());
        if (timeTable.getSixthPeriod() != null) response.setTime6(timeTable.getSixthPeriod());
        if (timeTable.getSeventhPeriod() != null) response.setTime7(timeTable.getSeventhPeriod());

        return response.setStatus(200);
    }
}
