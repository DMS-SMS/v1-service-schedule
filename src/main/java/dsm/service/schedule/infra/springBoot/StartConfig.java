package dsm.service.schedule.infra.springBoot;

import com.orbitz.consul.NotRegisteredException;
import dsm.service.schedule.infra.consul.ConsulHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartConfig implements CommandLineRunner {
    private final ConsulHandler consulHandler;

    @Override
    public void run(String... args) throws Exception {
        try {
            consulHandler.registerConsul();
        } catch (NotRegisteredException e) {
            System.exit(42);
        }
    }
}