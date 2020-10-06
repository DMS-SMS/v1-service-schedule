package dsm.service.schedule.grpc;

import dsm.service.schedule.proto.CreateScheduleRequest;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import dsm.service.schedule.proto.GetScheduleRequest;
import dsm.service.schedule.proto.GetScheduleResponse;
import dsm.service.schedule.proto.ScheduleServiceGrpc.ScheduleServiceImplBase;
import dsm.service.schedule.service.ScheduleService;
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
        responseObserver.onNext(scheduleService.getScheduleService());
        responseObserver.onCompleted();
    }
}
