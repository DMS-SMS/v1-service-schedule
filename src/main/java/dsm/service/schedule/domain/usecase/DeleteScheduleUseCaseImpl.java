package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.domain.exception.UnauthorizedException;
import dsm.service.schedule.domain.repository.ScheduleRepository;
import dsm.service.schedule.domain.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DeleteScheduleUseCaseImpl implements DeleteScheduleUseCase {
    private final ScheduleRepository scheduleRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void run(String teacherUuid, String xRequestId, String scheduleUuid) {
        Account account = teacherRepository.findById(teacherUuid, xRequestId)
                .orElseThrow(UnauthorizedException::new);

        scheduleRepository.findById(scheduleUuid).ifPresent(scheduleRepository::delete);
    }
}
