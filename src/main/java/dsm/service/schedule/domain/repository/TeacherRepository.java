package dsm.service.schedule.domain.repository;

import dsm.service.schedule.domain.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface TeacherRepository {
    Optional<Account> findById(String uuid, String xRequest);
}
