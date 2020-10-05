package dsm.service.schedule.domain.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name="schedule")
public class Schedule {
    @Id
    private String uuid;

    @Column(length = 20)
    private String teacherUuid;

    @Column(length = 100)
    private String title;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;
}
