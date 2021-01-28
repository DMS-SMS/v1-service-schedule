package dsm.service.schedule.infra.mq.sqs;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AwsSqsListener {
    private final AwsSqsService awsSqsService;

    @SqsListener(value = "${cloud.aws.sqs.url}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receive(String message, @Header("SenderId") String senderId) {
        awsSqsService.updateAllGrpcServiceAddress();
    }
}
