package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.repository.ScheduleRepository;
import dsm.service.schedule.domain.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteScheduleUseCaseImpl implements DeleteScheduleUseCase {
    private final ScheduleRepository scheduleRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void run(String teacherUuid, String scheduleUuid) {
        teacherRepository.findById(teacherUuid).flatMap(
                teacher -> scheduleRepository.findById(scheduleUuid)).ifPresent(scheduleRepository::delete);
    }
}
