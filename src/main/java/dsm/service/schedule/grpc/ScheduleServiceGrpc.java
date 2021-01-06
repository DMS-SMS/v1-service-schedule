package dsm.service.schedule.grpc;

import dsm.service.schedule.proto.*;
import dsm.service.schedule.proto.ScheduleServiceGrpc.ScheduleServiceImplBase;
import dsm.service.schedule.service.ScheduleService;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@AllArgsConstructor
public class ScheduleServiceGrpc extends ScheduleServiceImplBase {
    private final ScheduleService scheduleService;

    @Override
    public void createSchedule(CreateScheduleRequest request, StreamObserver<DefaultScheduleResponse> responseObserver) {
        responseObserver.onNext(scheduleService.createScheduleService(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getSchedule(GetScheduleRequest request, StreamObserver<GetScheduleResponse> responseObserver) {
        responseObserver.onNext(scheduleService.getScheduleService(request));
        responseObserver.onCompleted();
    }

    @Override
    public void updateSchedule(UpdateScheduleRequest request, StreamObserver<DefaultScheduleResponse> responseObserver) {
        responseObserver.onNext(scheduleService.updateScheduleService(request));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteSchedule(DeleteScheduleRequest request, StreamObserver<DefaultScheduleResponse> responseObserver) {
        responseObserver.onNext(scheduleService.deleteScheduleService(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getTimeTable(GetTimeTableRequest request, StreamObserver<GetTimeTableResponse> responseObserver) {
        responseObserver.onNext(scheduleService.getTimeTableService(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getTimeTables(GetTimeTablesRequest request, StreamObserver<GetTimeTablesResponse> responseObserver) {
        responseObserver.onNext(scheduleService.getTimeTablesService(request));
        responseObserver.onCompleted();
    }
}
