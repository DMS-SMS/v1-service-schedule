package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.Mapper;
import dsm.service.schedule.core.domain.entity.Timetable;
import dsm.service.schedule.core.usecase.Timetable.GetTimetablesUseCase;
import dsm.service.schedule.proto.GetTimeTablesResponse;
import dsm.service.schedule.proto.TimeTable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetTimetablesOutputMapper extends Mapper<GetTimetablesUseCase.OutputValues, GetTimeTablesResponse> {
    @Override
    public GetTimeTablesResponse map(GetTimetablesUseCase.OutputValues input) {
        GetTimeTablesResponse.Builder builder = GetTimeTablesResponse.newBuilder();
        return mapItems(builder, input.getTimetables()).setStatus(200).build();
    }

    private GetTimeTablesResponse.Builder mapItems(GetTimeTablesResponse.Builder builder, List<Timetable> timetables) {
        for (Timetable timetable: timetables) {
            TimeTable.Builder timetableBuilder = TimeTable.newBuilder();
            builder.addTimeTable(putSubjects(timetableBuilder, timetable));
        }
        return builder;
    }

    private TimeTable.Builder putSubjects(TimeTable.Builder builder, Timetable timetable) {
        if (timetable.getFirstSubject() != null) builder.setTime1(timetable.getFirstSubject());
        if (timetable.getSecondSubject() != null) builder.setTime2(timetable.getSecondSubject());
        if (timetable.getThirdSubject() != null) builder.setTime3(timetable.getThirdSubject());
        if (timetable.getFourthSubject() != null) builder.setTime4(timetable.getFourthSubject());
        if (timetable.getFifthSubject() != null) builder.setTime5(timetable.getFifthSubject());
        if (timetable.getSixthSubject() != null) builder.setTime6(timetable.getSixthSubject());
        if (timetable.getSeventhSubject() != null) builder.setTime7(timetable.getSeventhSubject());
        return builder;
    }
}
