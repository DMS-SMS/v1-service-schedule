package dsm.service.schedule.data.adapter.repository;

import dsm.service.schedule.core.domain.entity.Schedule;
import dsm.service.schedule.core.domain.repository.ScheduleRepository;
import dsm.service.schedule.data.adapter.repository.mapper.ScheduleRepositoryMapper;
import dsm.service.schedule.data.db.jpa.repository.JpaScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JpaScheduleRepository jpaScheduleRepository;

    private final ScheduleRepositoryMapper scheduleRepositoryMapper;

    @Override
    public Schedule persist(Schedule schedule) {
        jpaScheduleRepository.save(scheduleRepositoryMapper.map(schedule));
        return schedule;
    }

    @Override
    public void delete(Schedule schedule) {
        jpaScheduleRepository.delete(scheduleRepositoryMapper.map(schedule));
    }

    @Override
    public Optional<Schedule> findById(String id) {
        return scheduleRepositoryMapper.map(jpaScheduleRepository.findById(id));
    }

    @Override
    public List<Schedule> findByCorrectDate(String date) {
        System.out.println("ff");
        return scheduleRepositoryMapper.map(jpaScheduleRepository.findByCorrectDate(date));
    }
}
