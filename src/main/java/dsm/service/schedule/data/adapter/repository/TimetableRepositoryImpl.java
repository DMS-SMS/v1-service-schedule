package dsm.service.schedule.data.adapter.repository;

import dsm.service.schedule.core.domain.entity.Timetable;
import dsm.service.schedule.core.domain.repository.TimetableRepository;
import dsm.service.schedule.data.adapter.repository.mapper.TimetableRepositoryMapper;
import dsm.service.schedule.data.db.jpa.repository.JpaTimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TimetableRepositoryImpl implements TimetableRepository {
    private final JpaTimetableRepository jpaTimetableRepository;

    private final TimetableRepositoryMapper timetableRepositoryMapper;

    @Override
    public Timetable persist(Timetable timetable) {
        jpaTimetableRepository.save(timetableRepositoryMapper.map(timetable));
        return timetable;
    }

    @Override
    public Optional<Timetable> findByGradeAndGroupAndDate(Integer grade, Integer group, LocalDate date) {
        return timetableRepositoryMapper.map(
                jpaTimetableRepository.findByTargetGradeAndTargetGroupAndDate(grade, group, date));
    }
}
