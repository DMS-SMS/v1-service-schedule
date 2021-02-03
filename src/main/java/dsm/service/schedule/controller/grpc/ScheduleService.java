package dsm.service.schedule.controller.grpc;

import dsm.service.schedule.controller.mapper.*;
import dsm.service.schedule.core.usecase.timetable.GetTimetableUseCase;
import dsm.service.schedule.core.usecase.timetable.GetTimetablesUseCase;
import dsm.service.schedule.core.usecase.UseCaseExecutor;
import dsm.service.schedule.core.usecase.schedule.CreateScheduleUseCase;
import dsm.service.schedule.core.usecase.schedule.DeleteScheduleUseCase;
import dsm.service.schedule.core.usecase.schedule.GetScheduleUseCase;
import dsm.service.schedule.core.usecase.schedule.UpdateScheduleUseCase;
import dsm.service.schedule.proto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final UseCaseExecutor useCaseExecutor;

    private final CreateScheduleUseCase createScheduleUseCase;
    private final DeleteScheduleUseCase deleteScheduleUseCase;
    private final GetScheduleUseCase getScheduleUseCase;
    private final UpdateScheduleUseCase updateScheduleUseCase;
    private final GetTimetableUseCase getTimetableUseCase;
    private final GetTimetablesUseCase getTimetablesUseCase;

    private final CreateScheduleInputMapper createScheduleInputMapper;
    private final CreateScheduleOutputMapper createScheduleOutputMapper;
    private final GetScheduleInputMapper getScheduleInputMapper;
    private final GetScheduleOutputMapper getScheduleOutputMapper;
    private final UpdateScheduleInputMapper updateScheduleInputMapper;
    private final UpdateScheduleOutputMapper updateScheduleOutputMapper;
    private final DeleteScheduleInputMapper deleteScheduleInputMapper;
    private final DeleteScheduleOutputMapper deleteScheduleOutputMapper;
    private final GetTimetableInputMapper getTimetableInputMapper;
    private final GetTimetableOutputMapper getTimetableOutputMapper;
    private final GetTimetablesInputMapper getTimetablesInputMapper;
    private final GetTimetablesOutputMapper getTimetablesOutputMapper;


    public DefaultScheduleResponse createSchedule(CreateScheduleRequest request) {
        return useCaseExecutor.execute(
                createScheduleUseCase,
                createScheduleInputMapper.map(request),
                createScheduleOutputMapper
        );
    }

    public GetScheduleResponse getSchedule(GetScheduleRequest request) {
        return useCaseExecutor.execute(
                getScheduleUseCase,
                getScheduleInputMapper.map(request),
                getScheduleOutputMapper
        );
    }

    public DefaultScheduleResponse deleteSchedule(DeleteScheduleRequest request) {
        return useCaseExecutor.execute(
                deleteScheduleUseCase,
                deleteScheduleInputMapper.map(request),
                deleteScheduleOutputMapper
        );
    }

    public DefaultScheduleResponse updateSchedule(UpdateScheduleRequest request) {
        return useCaseExecutor.execute(
                updateScheduleUseCase,
                updateScheduleInputMapper.map(request),
                updateScheduleOutputMapper
        );
    }

    public GetTimeTableResponse getTimeTable(GetTimeTableRequest request) {
        return useCaseExecutor.execute(
                getTimetableUseCase,
                getTimetableInputMapper.map(request),
                getTimetableOutputMapper
        );
    }

    public GetTimeTablesResponse getTimeTables(GetTimeTablesRequest request) {
        return useCaseExecutor.execute(
                getTimetablesUseCase,
                getTimetablesInputMapper.map(request),
                getTimetablesOutputMapper
        );
    }
}
