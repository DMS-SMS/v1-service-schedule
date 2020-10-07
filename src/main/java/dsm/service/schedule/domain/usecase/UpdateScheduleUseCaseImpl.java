package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.repository.ScheduleRepository;
import dsm.service.schedule.domain.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;


@Component
@AllArgsConstructor
public class UpdateScheduleUseCaseImpl implements UpdateScheduleUseCase {
    private final ScheduleRepository scheduleRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void run(String teacherUuid, String scheduleUuid, String detail, Long startTime, Long endTime) {
        teacherRepository.findById(teacherUuid)
                .flatMap(teacher -> scheduleRepository.findById(scheduleUuid)).ifPresent(schedule -> {
                    schedule.setDetail(detail);
                    schedule.setStartDate(LocalDate.ofEpochDay(startTime));
                    schedule.setEndDate(LocalDate.ofEpochDay(endTime));
                    scheduleRepository.save(
                            schedule
                    );
                }
        );
    }
}
