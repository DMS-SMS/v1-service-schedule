package dsm.service.schedule.data.db.jpa.repository;

import dsm.service.schedule.data.db.jpa.model.TimetableModel;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface JpaTimetableRepository extends CrudRepository<TimetableModel, String> {
    Optional<TimetableModel> findByTargetGradeAndTargetGroupAndDate(Integer grade, Integer group, LocalDate date);
}
