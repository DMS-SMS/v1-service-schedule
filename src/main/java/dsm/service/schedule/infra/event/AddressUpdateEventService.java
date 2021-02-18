package dsm.service.schedule.infra.event;

import dsm.service.schedule.data.grpc.auth.AuthService;
import dsm.service.schedule.proto.Empty;
import dsm.service.schedule.proto.ScheduleEventGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@RequiredArgsConstructor
public class AddressUpdateEventService extends ScheduleEventGrpc.ScheduleEventImplBase {
    private final AuthService authService;

    @Override
    public void changeAllServiceNodes(Empty request, StreamObserver<Empty> responseObserver) {
        authService.updateServiceAddress();
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
