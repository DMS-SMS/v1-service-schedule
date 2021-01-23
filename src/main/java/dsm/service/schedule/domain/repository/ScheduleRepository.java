package dsm.service.schedule.domain.repository;

import dsm.service.schedule.domain.entity.Schedule;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, String> {
    @Override
    Iterable<Schedule> findAll();

    Iterable<Schedule> findByStartDateBetweenOrEndDateBetween(LocalDate startDate, LocalDate startDate2, LocalDate endDate, LocalDate endDate2);
}