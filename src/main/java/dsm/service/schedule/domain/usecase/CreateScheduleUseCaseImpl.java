package dsm.service.schedule.domain.usecase;


import dsm.service.schedule.domain.entity.Schedule;
import dsm.service.schedule.domain.repository.ScheduleRepository;
import dsm.service.schedule.domain.service.UuidService;
import jdk.vm.ci.meta.Local;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CreateScheduleUseCaseImpl implements CreateScheduleUseCase {
    final private ScheduleRepository scheduleRepository;

    final private UuidService uuidService;

    @Override
    public void run(
            String teacherUuid, String detail, Long startTime, Long endTime) {
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
