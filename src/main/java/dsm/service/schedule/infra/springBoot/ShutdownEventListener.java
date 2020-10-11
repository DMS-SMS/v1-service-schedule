package dsm.service.schedule.infra.springBoot;

import dsm.service.schedule.infra.consul.ConsulHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShutdownEventListener implements ApplicationListener<ContextClosedEvent> {
    private final ConsulHandler consulHandler;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        consulHandler.deregisterConsul();
    }
}
