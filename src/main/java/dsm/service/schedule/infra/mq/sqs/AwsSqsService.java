package dsm.service.schedule.infra.mq.sqs;

import dsm.service.schedule.infra.auth.AuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwsSqsService {
    private final AuthHandler authHandler;

    public void updateAllGrpcServiceAddress() {
        authHandler.updateServiceAddress();
    }
}
