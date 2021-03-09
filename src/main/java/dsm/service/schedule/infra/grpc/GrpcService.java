package dsm.service.schedule.infra.grpc;

import dsm.service.schedule.data.grpc.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpcService {
    private final AuthService authService;

    public void updateAllGrpcServiceAddress() {
        authService.updateServiceAddress();
    }
}