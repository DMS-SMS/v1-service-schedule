package dsm.service.schedule.data.adapter.repository.mapper;

import dsm.service.schedule.core.domain.entity.Timetable;
import dsm.service.schedule.data.db.jpa.model.TimetableModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TimetableRepositoryMapper {
    public Optional<Timetable> map(Optional<TimetableModel> optionalTimetableModel) {
        if (!optionalTimetableModel.isPresent()) return Optional.empty();
        TimetableModel timetableModel = optionalTimetableModel.get();

        return Optional.of(
                Timetable.builder()
                        .timetableUuid(timetableModel.getUuid())
                        .targetGrade(timetableModel.getTargetGrade())
                        .targetGroup(timetableModel.getTargetGroup())
                        .date(timetableModel.getDate())
                        .firstSubject(timetableModel.getFirstSubject())
                        .secondSubject(timetableModel.getSecondSubject())
                        .thirdSubject(timetableModel.getThirdSubject())
                        .fourthSubject(timetableModel.getFourthSubject())
                        .fifthSubject(timetableModel.getFifthSubject())
                        .sixthSubject(timetableModel.getSixthSubject())
                        .seventhSubject(timetableModel.getSeventhSubject())
                        .build()
        );
    }

    public TimetableModel map(Timetable timetable) {
        return TimetableModel.builder()
                .uuid(timetable.getTimetableUuid())
                .targetGrade(timetable.getTargetGrade())
                .targetGroup(timetable.getTargetGroup())
                .firstSubject(timetable.getFirstSubject())
                .secondSubject(timetable.getSecondSubject())
                .thirdSubject(timetable.getThirdSubject())
                .fourthSubject(timetable.getFourthSubject())
                .fifthSubject(timetable.getFifthSubject())
                .sixthSubject(timetable.getSixthSubject())
                .seventhSubject(timetable.getSeventhSubject())
                .build();
    }
}
