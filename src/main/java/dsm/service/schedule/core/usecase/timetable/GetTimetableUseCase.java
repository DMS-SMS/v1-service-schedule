package dsm.service.schedule.core.usecase.timetable;

import dsm.service.schedule.core.domain.entity.Account;
import dsm.service.schedule.core.domain.entity.Timetable;
import dsm.service.schedule.core.domain.entity.enums.AccountType;
import dsm.service.schedule.core.domain.exception.NotFoundException;
import dsm.service.schedule.core.domain.exception.UnauthorizedException;
import dsm.service.schedule.core.domain.repository.TimetableRepository;
import dsm.service.schedule.core.usecase.UseCase;
import dsm.service.schedule.core.usecase.account.GetAccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class GetTimetableUseCase extends UseCase<GetTimetableUseCase.InputValues, GetTimetableUseCase.OutputValues> {
    private final TimetableRepository timetableRepository;

    private final GetAccountUseCase getAccountUseCase;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(getTimetable(input));
    }

    public Timetable getTimetable(InputValues input) {
        Account account = getAccount(input.getAccountUuid());
        checkAuthority(account);

        return timetableRepository.findByGradeAndGroupAndDate(
                account.getGrade(), account.getGroup(), generateDate(input.year, input.month, input.day)
        ).orElseThrow(NotFoundException::new);
    }

    private void checkAuthority(Account account) {
        if (account.getType() != AccountType.STUDENT) throw new UnauthorizedException();
    }

    private Account getAccount(String accountUuid) {
        return getAccountUseCase.execute(new GetAccountUseCase.InputValues(accountUuid)).getAccount()
                .orElseThrow(UnauthorizedException::new);
    }

    private LocalDate generateDate(Integer year, Integer month, Integer day) {
        return LocalDate.of(year, month, day);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        String accountUuid;
        Integer year;
        Integer month;
        Integer day;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Timetable timetable;
    }
}
