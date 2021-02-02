package dsm.service.schedule.infra.config.spring;

import com.orbitz.consul.NotRegisteredException;
import dsm.service.schedule.infra.consul.ConsulService;
import dsm.service.schedule.infra.ms.sqs.AwsSqsService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartConfig implements CommandLineRunner {
    private final ConsulService consulService;
    private final AwsSqsService awsSqsService;

    @Override
    public void run(String... args) throws Exception {
        try {
            consulService.registerConsul();
            awsSqsService.updateAllGrpcServiceAddress();
        } catch (NotRegisteredException e) {
            System.exit(42);
        }
    }
}