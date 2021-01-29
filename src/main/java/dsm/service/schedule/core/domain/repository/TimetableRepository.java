package dsm.service.schedule.core.domain.repository;

import dsm.service.schedule.core.domain.entity.Timetable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TimetableRepository {
    Timetable persist(Timetable timetable);

    Optional<Timetable> findByGradeAndGroupAndDate(Integer grade, Integer group, LocalDate date);
}
