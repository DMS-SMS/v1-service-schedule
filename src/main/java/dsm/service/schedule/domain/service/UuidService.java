package dsm.service.schedule.domain.service;


import org.springframework.stereotype.Component;

@Component
public interface UuidService {
    public String generateUuid();

    public boolean checkUuid(String uuid);

    public String generateRandomKey();
}
