package dsm.service.schedule.controller.mapper;

import dsm.service.schedule.core.usecase.timetable.GetTimetableUseCase;
import dsm.service.schedule.proto.GetTimeTableRequest;
import org.springframework.stereotype.Component;

@Component
public class GetTimetableInputMapper {
    public GetTimetableUseCase.InputValues map(GetTimeTableRequest request) {
        return new GetTimetableUseCase.InputValues(
                request.getUuid(),
                request.getYear(),
                request.getMonth(),
                request.getDay()
        );
    }
}
