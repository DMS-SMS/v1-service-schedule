package dsm.service.schedule.domain.repository;

import dsm.service.schedule.domain.entity.Timetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable, String> {
    @Override
    Optional<Timetable> findById(String s);

    Optional<Timetable> findByTargetGradeAndTargetGroupAndDay(
            Integer targetGrade, Integer targetGroup, String day);
}
