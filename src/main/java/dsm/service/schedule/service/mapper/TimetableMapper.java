package dsm.service.schedule.service.mapper;

import dsm.service.schedule.domain.entity.Timetable;
import dsm.service.schedule.proto.GetTimeTableResponse;
import org.springframework.stereotype.Component;

@Component
public class TimetableMapper {
    public GetTimeTableResponse.Builder getTimetableMapper(
            Timetable timetable
    ) {
        GetTimeTableResponse.Builder response = GetTimeTableResponse.newBuilder();
        if (timetable.getFirstPeriod() != null) response.setTime1(timetable.getFirstPeriod());
        if (timetable.getSecondPeriod() != null) response.setTime2(timetable.getSecondPeriod());
        if (timetable.getThirdPeriod() != null) response.setTime3(timetable.getThirdPeriod());
        if (timetable.getFourthPeriod() != null) response.setTime4(timetable.getFourthPeriod());
        if (timetable.getFifthPeriod() != null) response.setTime5(timetable.getFifthPeriod());
        if (timetable.getSixthPeriod() != null) response.setTime6(timetable.getSixthPeriod());
        if (timetable.getSeventhPeriod() != null) response.setTime7(timetable.getSeventhPeriod());

        return response.setStatus(200);
    }
}
