package dsm.service.schedule.infra.springBoot;

import com.orbitz.consul.NotRegisteredException;
import dsm.service.schedule.infra.consul.ConsulHandler;
import dsm.service.schedule.infra.mq.sqs.AwsSqsService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartConfig implements CommandLineRunner {
    private final ConsulHandler consulHandler;
    private final AwsSqsService awsSqsService;

    @Override
    public void run(String... args) throws Exception {
        try {
            consulHandler.registerConsul();
            awsSqsService.updateAllGrpcServiceAddress();
        } catch (NotRegisteredException e) {
            System.exit(42);
        }
    }
}
