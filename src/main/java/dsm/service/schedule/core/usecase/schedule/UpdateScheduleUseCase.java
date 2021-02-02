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

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class UpdateScheduleUseCase extends UseCase<UpdateScheduleUseCase.InputValues, UpdateScheduleUseCase.OutputValues> {
    private final ScheduleRepository scheduleRepository;

    private final GetAccountUseCase getAccountUseCase;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(scheduleRepository.persist(updateSchedule(input)));
    }

    private Schedule updateSchedule(InputValues input) {
        checkAuthority(input.teacherUuid);
        Schedule schedule =  scheduleRepository.findById(input.scheduleUuid).orElseThrow(NotFoundException::new);
        Timestamp startTimestamp = generateTimestamp(input.startTime);
        Timestamp endTimeStamp = generateTimestamp(input.endTime);

        return schedule.updateSchedule(
                input.detail,
                startTimestamp.toLocalDateTime().toLocalDate(),
                endTimeStamp.toLocalDateTime().toLocalDate()
        );
    }

    private void checkAuthority(String accountUuid) {
        Account account = getAccountUseCase.execute(new GetAccountUseCase.InputValues(accountUuid)).getAccount().
                orElseThrow(UnauthorizedException::new);
        if (account.getType() != AccountType.TEACHER && account.getType() != AccountType.ADMIN) {
            throw new UnauthorizedException();
        }
    }

    private Timestamp generateTimestamp(Long secondTimestamp) {
        return new Timestamp(secondTimestamp*1000);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        String teacherUuid;
        String scheduleUuid;
        String detail;
        Long startTime;
        Long endTime;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Schedule schedule;
    }
}
