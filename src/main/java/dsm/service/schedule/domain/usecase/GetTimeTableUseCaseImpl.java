package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.domain.entity.Timetable;
import dsm.service.schedule.domain.exception.NotFoundException;
import dsm.service.schedule.domain.exception.UnauthorizedException;
import dsm.service.schedule.domain.repository.StudentRepository;
import dsm.service.schedule.domain.repository.TimetableRepository;
import dsm.service.schedule.domain.service.TimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class GetTimeTableUseCaseImpl implements GetTimeTableUseCase {
    private final StudentRepository studentRepository;

    private final TimetableRepository timetableRepository;

    private final TimeService timeService;

    @Override
    public Timetable execute(String uuid, Integer year, Integer month, Integer day) {
        Account student = studentRepository.findById(uuid)
                .orElseThrow(UnauthorizedException::new);

        return timetableRepository.findByTargetGradeAndTargetGroupAndDay(
                student.getGrade(),
                student.getGroup(),
                year.toString()+month.toString()+day.toString()
        ).orElseThrow(NotFoundException::new);
    }
}
