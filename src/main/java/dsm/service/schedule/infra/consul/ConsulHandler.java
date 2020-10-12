package dsm.service.schedule.infra.consul;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import com.orbitz.consul.model.health.Service;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Component
public class ConsulHandler {
    private String serviceId = "DMS.SMS.v1.service.schedule";
    private Consul client = Consul.builder().withUrl("http://127.0.0.1:8500").build();
    private AgentClient agentClient = client.agentClient();
    private KeyValueClient keyValueClient = client.keyValueClient();

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

    public JSONObject getValue(String key){
        JSONParser parser = new JSONParser();
        String value = keyValueClient.getValueAsString(key).orElse("{}");
        try {
            return (JSONObject) parser.parse(value);
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    public String getServiceHost(String serviceName) {
        return Objects.requireNonNull(getService(serviceName)).getAddress();
    }

    public Integer getServicePort(String serviceName) {
        return Objects.requireNonNull(getService(serviceName)).getPort();
    }

    private Service getService(String serviceName) {
        for(Map.Entry<String, Service> elem:agentClient.getServices().entrySet()) {
            if(elem.getValue().getService().equals(serviceName)) {
                if(agentClient.getChecks().get("service:"+elem.getValue().getId()).getStatus().equals("passing"))
                    return elem.getValue();
            }
        }
        return null;
    }
}
