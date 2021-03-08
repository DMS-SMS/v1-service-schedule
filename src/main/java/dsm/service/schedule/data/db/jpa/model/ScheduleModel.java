package dsm.service.schedule.data.db.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@Getter
@Entity(name="tbl_schedule")
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleModel {
    @Id
    private String uuid;

    @Column(length = 100, nullable = false)
    private String detail;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;
}