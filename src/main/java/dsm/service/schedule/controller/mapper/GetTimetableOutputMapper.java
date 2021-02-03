package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.Mapper;
import dsm.service.schedule.core.domain.entity.Timetable;
import dsm.service.schedule.core.usecase.timetable.GetTimetableUseCase;
import dsm.service.schedule.proto.GetTimeTableResponse;
import org.springframework.stereotype.Component;

@Component
public class GetTimetableOutputMapper extends Mapper<GetTimetableUseCase.OutputValues, GetTimeTableResponse> {
    @Override
    public GetTimeTableResponse map(GetTimetableUseCase.OutputValues input) {
        GetTimeTableResponse.Builder response = GetTimeTableResponse.newBuilder();
        Timetable timetable = input.getTimetable();

        if (timetable.getFirstSubject() != null) response.setTime1(timetable.getFirstSubject());
        if (timetable.getSecondSubject() != null) response.setTime2(timetable.getSecondSubject());
        if (timetable.getThirdSubject() != null) response.setTime3(timetable.getThirdSubject());
        if (timetable.getFourthSubject() != null) response.setTime4(timetable.getFourthSubject());
        if (timetable.getFifthSubject() != null) response.setTime5(timetable.getFifthSubject());
        if (timetable.getSixthSubject() != null) response.setTime6(timetable.getSixthSubject());
        if (timetable.getSeventhSubject() != null) response.setTime7(timetable.getSeventhSubject());

        return response.setStatus(200).build();
    }
}
