package dsm.service.schedule.infra.implementation.service;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.repository.ScheduleRepository;
import dsm.service.schedule.domain.service.UuidService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
@AllArgsConstructor
public class UuidServiceImpl implements UuidService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public boolean checkUuid(String uuid) {
        return scheduleRepository.findById(uuid).isPresent();
    }

    @Override
    public String generateRandomKey() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 13) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    @Override
    public String generateUuid() {
        String uuid = "schedule-"+generateRandomKey();
        while (checkUuid(uuid)) {
            uuid = "schedule-"+generateRandomKey();
        }
        return uuid;
    }
}
