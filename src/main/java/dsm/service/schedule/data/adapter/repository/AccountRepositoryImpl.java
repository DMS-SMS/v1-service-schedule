package dsm.service.schedule.data.adapter.repository;

import dsm.service.schedule.core.domain.entity.Account;
import dsm.service.schedule.core.domain.entity.enums.AccountType;
import dsm.service.schedule.core.domain.exception.ServerErrorException;
import dsm.service.schedule.core.domain.repository.AccountRepository;
import dsm.service.schedule.data.adapter.repository.mapper.AccountRepositoryMapper;
import dsm.service.schedule.data.grpc.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountRepositoryMapper accountRepositoryMapper;

    private final AuthService authService;

    @Override
    public Optional<Account> findAdminByUuid(String adminUuid, String accountUuid) {
        return Optional.of(new Account(0, 0, "어드민", "0", AccountType.ADMIN));
    }

    @Override
    public Optional<Account> findParentsByUuid(String parentsUuid, String accountUuid) {
        return Optional.of(new Account(0, 0, "학부모", "0", AccountType.PARENTS));
    }

    @Override
    public Optional<Account> findStudentByUuid(String studentUuid, String accountUuid) {
        try {
            return accountRepositoryMapper.map(authService.getStudentInform(studentUuid, accountUuid));
        } catch (InterruptedException e) {
            throw new ServerErrorException("Auth server (Student) timeout (3s) on Schedule Service. Detail : \n "+ e.getMessage());
        } catch (NullPointerException e) {
            throw new ServerErrorException(503, "Can not found service in Consul");
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Account> findTeacherByUuid(String teacherUuid, String accountUuid) {
        try {
            return accountRepositoryMapper.map(authService.getTeacherInform(teacherUuid, accountUuid));
        } catch (InterruptedException e) {
            throw new ServerErrorException("Auth server (Student) timeout (3s) on Schedule Service. Detail : \n "+ e.getMessage());
        } catch (NullPointerException e) {
            throw new ServerErrorException(503, "Can not found service in Consul");
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }
}
