package dsm.service.schedule.domain.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@Setter
@Getter
@Entity(name="tbl_schedule")
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    private String uuid;

    @Column(length = 20, nullable = false)
    private String teacherUuid;

    @Column(length = 100, nullable = false)
    private String detail;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;
}
