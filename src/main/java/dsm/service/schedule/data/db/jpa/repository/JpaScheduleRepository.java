package dsm.service.schedule.data.db.jpa.repository;

import dsm.service.schedule.data.db.jpa.model.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaScheduleRepository extends JpaRepository<ScheduleModel, String> {
    @Query("SELECT s " +
            "FROM test_tbl_schedule s " +
            "WHERE ?1 BETWEEN " +
            "FUNCTION('date_format', s.startDate, '%Y, %m') AND FUNCTION('date_format', s.endDate, '%Y, %m')")
    List<ScheduleModel> findByCorrectDate(String date);
}
