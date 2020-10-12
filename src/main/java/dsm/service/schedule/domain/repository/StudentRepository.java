package dsm.service.schedule.domain.repository;

import dsm.service.schedule.domain.entity.Account;

import java.util.Optional;

public interface StudentRepository {
    Optional<Account> findById(String uuid, String xRequest);
}
