package dsm.service.schedule.core.domain.repository;

import dsm.service.schedule.core.domain.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository {
    Optional<Account> findAdminByUuid(String adminUuid, String accountUuid);

    Optional<Account> findStudentByUuid(String studentUuid, String accountUuid);

    Optional<Account> findTeacherByUuid(String teacherUuid, String accountUuid);

    Optional<Account> findParentsByUuid(String parentsUuid, String accountUuid);
}
