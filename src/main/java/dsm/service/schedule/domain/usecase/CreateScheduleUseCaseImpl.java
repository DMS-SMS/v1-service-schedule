package dsm.service.schedule.domain.usecase;


import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.exception.UnauthorizedException;
import dsm.service.schedule.domain.repository.ScheduleRepository;
import dsm.service.schedule.domain.repository.TeacherRepository;
import dsm.service.schedule.domain.service.UuidService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CreateScheduleUseCaseImpl implements CreateScheduleUseCase {
    final private ScheduleRepository scheduleRepository;
    final private TeacherRepository teacherRepository;

    final private UuidService uuidService;

    @Override
    public void run(
            String teacherUuid, String detail, Long startTime, Long endTime
    ) {
        Account account = teacherRepository.findById(teacherUuid)
                .orElseThrow(UnauthorizedException::new);


        scheduleRepository.save(
                Schedule.builder()
                        .uuid(uuidService.generateUuid())
                        .teacherUuid(teacherUuid)
                        .detail(detail)
                        .startDate(LocalDate.ofEpochDay(startTime))
                        .endDate(LocalDate.ofEpochDay(endTime))
                        .build()
        );
    }
}
