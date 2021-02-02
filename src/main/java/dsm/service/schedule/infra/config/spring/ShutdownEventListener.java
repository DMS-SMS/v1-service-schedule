package dsm.service.schedule.infra.config.spring;

import dsm.service.schedule.infra.consul.ConsulService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShutdownEventListener implements ApplicationListener<ContextClosedEvent> {
    private final ConsulService consulService;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        consulService.deregisterConsul();
    }
}