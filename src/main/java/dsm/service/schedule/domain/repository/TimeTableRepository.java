package dsm.service.schedule.domain.repository;

import dsm.service.schedule.domain.entity.TimeTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Optional;

@Repository
public interface TimeTableRepository extends CrudRepository<TimeTable, String> {
    @Override
    Optional<TimeTable> findById(String s);

    Optional<TimeTable> findByTargetGradeAndTargetGroupAndWeek(
            Integer targetGrade, Integer targetGroup, Integer week);
}
