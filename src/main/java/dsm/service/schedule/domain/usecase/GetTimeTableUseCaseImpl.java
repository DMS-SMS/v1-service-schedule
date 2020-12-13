package dsm.service.schedule.domain.usecase;

import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.domain.entity.TimeTable;
import dsm.service.schedule.domain.exception.NotFoundException;
import dsm.service.schedule.domain.exception.UnauthorizedException;
import dsm.service.schedule.domain.repository.StudentRepository;
import dsm.service.schedule.domain.repository.TimeTableRepository;
import dsm.service.schedule.domain.service.TimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class GetTimeTableUseCaseImpl implements GetTimeTableUseCase {
    private final StudentRepository studentRepository;

    private final TimeTableRepository timeTableRepository;

    private final TimeService timeService;

    @Override
    public TimeTable execute(String uuid, Integer weekNumber) {
        Account student = studentRepository.findById(uuid)
                .orElseThrow(UnauthorizedException::new);

        if (weekNumber == 0) { weekNumber = timeService.getWeekNumber(); }

        return timeTableRepository.findByTargetGradeAndTargetGroupAndWeek(
                student.getGrade(),
                student.getGroup(),
                weekNumber
        ).orElseThrow(NotFoundException::new);
    }
}
