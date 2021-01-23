package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.exception.UnauthorizedException;
import dsm.service.schedule.domain.repository.ScheduleRepository;
import dsm.service.schedule.domain.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;


@Component
@AllArgsConstructor
public class UpdateScheduleUseCaseImpl implements UpdateScheduleUseCase {
    private final ScheduleRepository scheduleRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void execute(
            String teacherUuid, String scheduleUuid, String detail, Long startTime, Long endTime
    ) {
        Account account = teacherRepository.findById(teacherUuid)
                .orElseThrow(UnauthorizedException::new);

        Timestamp startTimestamp = new Timestamp(startTime*1000);
        Timestamp endTimestamp = new Timestamp(endTime*1000);

        scheduleRepository.findById(scheduleUuid)
                .ifPresent(schedule -> {
                    schedule.setDetail(detail);
                    schedule.setStartDate(startTimestamp.toLocalDateTime().toLocalDate());
                    schedule.setEndDate(endTimestamp.toLocalDateTime().toLocalDate());
                    scheduleRepository.save(
                            schedule
                    );
                }
        );
    }
}
