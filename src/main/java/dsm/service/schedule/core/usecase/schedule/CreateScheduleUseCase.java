package dsm.service.schedule.core.usecase.schedule;

import dsm.service.schedule.core.domain.entity.Schedule;
import dsm.service.schedule.core.domain.entity.enums.AccountType;
import dsm.service.schedule.core.domain.exception.UnauthorizedException;
import dsm.service.schedule.core.domain.repository.ScheduleRepository;
import dsm.service.schedule.core.usecase.UseCase;
import dsm.service.schedule.core.usecase.account.GetAccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class CreateScheduleUseCase extends UseCase<CreateScheduleUseCase.InputValues, CreateScheduleUseCase.OutputValues> {
    private final GetAccountUseCase getAccountUseCase;
    private final ScheduleRepository scheduleRepository;

    @Override
    public OutputValues execute(InputValues input) {
        checkAuthority(input.teacherUuid);
        return new OutputValues(scheduleRepository.persist(generateSchedule(input)));
    }

    private void checkAuthority(String accountUuid) {
        getAccountUseCase.execute(new GetAccountUseCase.InputValues(accountUuid)).getAccount().ifPresentOrElse(
                account -> {
                    if (account.getType() != AccountType.TEACHER && account.getType() != AccountType.ADMIN) {
                        throw new UnauthorizedException();
                    }
                }, () -> { throw new UnauthorizedException(); }
        );
    }

    private Schedule generateSchedule(InputValues input) {
        Timestamp startTimestamp = generateTimestamp(input.startTime);
        Timestamp endTimestamp = generateTimestamp(input.endTime);

        return new Schedule(
                generateScheduleUuid(),
                input.teacherUuid,
                startTimestamp.toLocalDateTime().toLocalDate(),
                endTimestamp.toLocalDateTime().toLocalDate()
        );
    }

    private Timestamp generateTimestamp(Long secondTimestamp) {
        return new Timestamp(secondTimestamp*1000);
    }

    private String generateScheduleUuid() {
        String uuid = "schedule-"+generateRandomKey();
        while (checkUuid(uuid)) {
            uuid = "schedule-"+generateRandomKey();
        }
        return uuid;
    }

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

    public boolean checkUuid(String uuid) {
        return scheduleRepository.findById(uuid).isPresent();
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        String teacherUuid;
        String detail;
        Long startTime;
        Long endTime;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Schedule schedule;
    }
}
