package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.usecase.Timetable.GetTimetablesUseCase;
import dsm.service.schedule.proto.GetTimeTablesRequest;
import org.springframework.stereotype.Component;

@Component
public class GetTimetablesInputMapper {
    public GetTimetablesUseCase.InputValues map(GetTimeTablesRequest request) {
        return new GetTimetablesUseCase.InputValues(
                request.getUuid(),
                request.getYear(),
                request.getMonth(),
                request.getDay(),
                request.getCount()
        );
    }
}
