package dsm.service.schedule.data.db.jpa.repository;

import dsm.service.schedule.data.db.jpa.model.ScheduleModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaScheduleRepository extends CrudRepository<ScheduleModel, String> {
    @Query("FROM test_tbl_schedule s " +
            "WHERE :date BETWEEN " +
            "FUNCTION('date_format', s.startDate, '%Y-%m') AND FUNCTION('date_format', s.endDate, '%Y-%m')")
    List<ScheduleModel> findByCorrectDate(@Param("date") String date);
}
