package dsm.service.schedule.infra.consul;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class ConsulHandler {
    private String serviceId = "DMS.SMS.v1.service.schedule";
    private Consul client = Consul.builder().withUrl("http://127.0.0.1:8500").build();
    private AgentClient agentClient = client.agentClient();

    public void registerConsul() throws NotRegisteredException {
        ImmutableRegistration service = ImmutableRegistration.builder()
                .id(serviceId)
                .name("DMS.SMS.v1.service.schedule")
                .port(10444)
                .check(Registration.RegCheck.ttl(1000000L))
                .tags(Collections.singletonList("schedule"))
                .meta(Collections.singletonMap("version","0.0.1"))
                .build();

        agentClient.register(service);
        agentClient.pass(serviceId);
    }

    public void deregisterConsul() {
        agentClient.deregister(serviceId);
    }
}
