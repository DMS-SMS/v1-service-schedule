package dsm.service.schedule.core.usecase.schedule;

import dsm.service.schedule.core.domain.entity.Account;
import dsm.service.schedule.core.domain.entity.Schedule;
import dsm.service.schedule.core.domain.entity.enums.AccountType;
import dsm.service.schedule.core.domain.exception.NotFoundException;
import dsm.service.schedule.core.domain.exception.UnauthorizedException;
import dsm.service.schedule.core.domain.repository.ScheduleRepository;
import dsm.service.schedule.core.usecase.UseCase;
import dsm.service.schedule.core.usecase.account.GetAccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeleteScheduleUseCase extends UseCase<DeleteScheduleUseCase.InputValues, DeleteScheduleUseCase.OutputValues> {
    private final ScheduleRepository scheduleRepository;
    private final GetAccountUseCase getAccountUseCase;

    @Override
    public OutputValues execute(InputValues input) {
        Schedule schedule = getSchedule(input.scheduleUuid);
        deleteSchedule(schedule, input.teacherUuid);
        return new OutputValues(schedule);
    }

    private Schedule getSchedule(String scheduleUuid) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleUuid);
        if (schedule.isPresent()) return schedule.get();
        throw new NotFoundException();
    }

    private void deleteSchedule(Schedule schedule, String accountUuid) {
        checkAuthority(accountUuid);
        scheduleRepository.delete(schedule);
    }

    private void checkAuthority(String accountUuid) {
        Account account = getAccountUseCase.execute(new GetAccountUseCase.InputValues(accountUuid)).getAccount().
                orElseThrow(UnauthorizedException::new);
        if (account.getType() != AccountType.TEACHER && account.getType() != AccountType.ADMIN) {
            throw new UnauthorizedException();
        }
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
         String teacherUuid;
         String scheduleUuid;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Schedule schedule;
    }
}
