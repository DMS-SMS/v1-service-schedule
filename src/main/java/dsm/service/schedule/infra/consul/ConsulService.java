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
import org.lognet.springboot.grpc.context.LocalRunningGrpcPort;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class ConsulService {
    private final String serviceName = "DMS.SMS.v1.service.schedule";
    private final String serviceId = String.format("%s-%s", serviceName, UUID.randomUUID());
    private final Consul client = Consul.builder().withUrl(String.format("http://%s", System.getenv("CONSUL_ADDR"))).build();
    private final AgentClient agentClient = client.agentClient();
    private final KeyValueClient keyValueClient = client.keyValueClient();
    @LocalRunningGrpcPort private Integer port;
    private final String address = InetAddress.getLocalHost().getHostAddress();

    public ConsulService() throws UnknownHostException {
    }

    public void registerConsul() throws NotRegisteredException {
        System.out.println(address);
        ImmutableRegistration service = ImmutableRegistration.builder()
                .id(serviceId)
                .name("DMS.SMS.v1.service.schedule")
                .address(address)
                .port(port)
                .check(Registration.RegCheck.ttl(31536000L))
                .tags(Collections.singletonList("schedule"))
                .meta(Collections.singletonMap("version","0.8"))
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