package dsm.service.schedule.core.domain.repository;

import dsm.service.schedule.core.domain.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository {
    Schedule persist(Schedule schedule);

    void delete(Schedule schedule);

    Optional<Schedule> findById(String id);

    List<Schedule> findByCorrectDate(String date);
}
