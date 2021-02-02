package dsm.service.schedule.data.adapter.repository.mapper;

import dsm.service.schedule.core.domain.entity.Schedule;
import dsm.service.schedule.data.db.jpa.model.ScheduleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScheduleRepositoryMapper {
    public ScheduleModel map(Schedule schedule) {
        return ScheduleModel.builder()
                .uuid(schedule.getScheduleUuid())
                .detail(schedule.getDetail())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build();
    }

    public Optional<Schedule> map(Optional<ScheduleModel> optionalScheduleModel) {
        if (!optionalScheduleModel.isPresent()) return Optional.empty();

        ScheduleModel scheduleModel = optionalScheduleModel.get();

        return Optional.of(
                Schedule.builder()
                .scheduleUuid(scheduleModel.getUuid())
                .detail(scheduleModel.getDetail())
                .startDate(scheduleModel.getStartDate())
                .endDate(scheduleModel.getEndDate())
                .build()
        );
    }

    public List<Schedule> map(List<ScheduleModel> scheduleModels) {
        List<Schedule> schedules = new ArrayList<Schedule>();

        for (ScheduleModel scheduleModel: scheduleModels) {
            schedules.add(
                    Schedule.builder()
                            .scheduleUuid(scheduleModel.getUuid())
                            .detail(scheduleModel.getDetail())
                            .startDate(scheduleModel.getStartDate())
                            .endDate(scheduleModel.getEndDate())
                            .build()
            );
        }

        return schedules;
    }
}
