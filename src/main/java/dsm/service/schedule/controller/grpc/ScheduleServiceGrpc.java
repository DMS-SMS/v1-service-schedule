package dsm.service.schedule.controller.grpc;

import dsm.service.schedule.proto.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

import dsm.service.schedule.proto.ScheduleServiceGrpc.ScheduleServiceImplBase;

@GRpcService
@AllArgsConstructor
public class ScheduleServiceGrpc extends ScheduleServiceImplBase {
    private final ScheduleService scheduleService;

    @Override
    public void createSchedule(CreateScheduleRequest request, StreamObserver<DefaultScheduleResponse> responseObserver) {
        responseObserver.onNext(scheduleService.createSchedule(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getSchedule(GetScheduleRequest request, StreamObserver<GetScheduleResponse> responseObserver) {
        responseObserver.onNext(scheduleService.getSchedule(request));
        responseObserver.onCompleted();
    }

    @Override
    public void updateSchedule(UpdateScheduleRequest request, StreamObserver<DefaultScheduleResponse> responseObserver) {
        responseObserver.onNext(scheduleService.updateSchedule(request));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteSchedule(DeleteScheduleRequest request, StreamObserver<DefaultScheduleResponse> responseObserver) {
        responseObserver.onNext(scheduleService.deleteSchedule(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getTimeTable(GetTimeTableRequest request, StreamObserver<GetTimeTableResponse> responseObserver) {
        responseObserver.onNext(scheduleService.getTimeTable(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getTimeTables(GetTimeTablesRequest request, StreamObserver<GetTimeTablesResponse> responseObserver) {
        responseObserver.onNext(scheduleService.getTimeTables(request));
        responseObserver.onCompleted();
    }
}
