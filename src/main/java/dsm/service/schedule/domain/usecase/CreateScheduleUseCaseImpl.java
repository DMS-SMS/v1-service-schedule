package dsm.service.schedule.domain.usecase;


import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.exception.UnauthorizedException;
import dsm.service.schedule.domain.repository.ScheduleRepository;
import dsm.service.schedule.domain.repository.TeacherRepository;
import dsm.service.schedule.domain.service.UuidService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateScheduleUseCaseImpl implements CreateScheduleUseCase {
    final private ScheduleRepository scheduleRepository;
    final private TeacherRepository teacherRepository;

    final private UuidService uuidService;

    @Override
    public String execute(
            String teacherUuid, String detail, Long startTime, Long endTime
    ) {
        Account account = teacherRepository.findById(teacherUuid)
                .orElseThrow(UnauthorizedException::new);

        Timestamp startTimeStamp = new Timestamp(startTime*1000);
        Timestamp endTimeStamp = new Timestamp(endTime*1000);

        String schedule_uuid = uuidService.generateUuid();

        scheduleRepository.save(
                Schedule.builder()
                        .uuid(schedule_uuid)
                        .teacherUuid(teacherUuid)
                        .detail(detail)
                        .startDate(startTimeStamp.toLocalDateTime().toLocalDate())
                        .endDate(endTimeStamp.toLocalDateTime().toLocalDate())
                        .build()
        );

        return schedule_uuid;
    }
}
