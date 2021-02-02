package dsm.service.schedule.infra.ms.sqs;

import dsm.service.schedule.data.grpc.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwsSqsService {
    private final AuthService authService;

    public void updateAllGrpcServiceAddress() {
        authService.updateServiceAddress();
    }
}