package dsm.service.schedule.core.domain.repository;

import dsm.service.schedule.core.domain.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {
    public Account findAdminByUuid(String adminUuid, String accountUuid);

    public Account findStudentByUuid(String studentUuid, String accountUuid);

    public Account findTeacherByUuid(String teacherUuid, String accountUuid);

    public Account findParentsByUuid(String parentsUuid, String accountUuid);
}
