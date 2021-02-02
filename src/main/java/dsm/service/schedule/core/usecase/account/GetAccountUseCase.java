package dsm.service.schedule.core.usecase.account;

import dsm.service.schedule.core.domain.entity.Account;
import dsm.service.schedule.core.domain.repository.AccountRepository;
import dsm.service.schedule.core.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetAccountUseCase extends UseCase<GetAccountUseCase.InputValues, GetAccountUseCase.OutputValues> {
    private final AccountRepository accountRepository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(getAccount(input.accountUuid));
    }

    private Optional<Account> getAccount(String accountUuid) {
        switch (accountUuid.substring(0, accountUuid.length() - 13)) {
            case "admin": return accountRepository.findAdminByUuid(accountUuid, accountUuid);
            case "student": return accountRepository.findStudentByUuid(accountUuid, accountUuid);
            case "teacher": return accountRepository.findTeacherByUuid(accountUuid, accountUuid);
            case "parents": return accountRepository.findParentsByUuid(accountUuid, accountUuid);
            default: return Optional.empty();
        }
    }

    @Value
    static public class InputValues implements UseCase.InputValues {
        String accountUuid;
    }

    @Value
    static public class OutputValues implements UseCase.OutputValues {
        Optional<Account> account;
    }
}
