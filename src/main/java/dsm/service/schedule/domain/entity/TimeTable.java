package dsm.service.schedule.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity(name="tbl_time_table")
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {
    @Id
    private String uuid;

    @Column(nullable = false)
    private Integer targetGrade;

    @Column(nullable = false)
    private Integer targetGroup;

    @Column(nullable = false)
    private Integer week;

    @Column(length = 20)
    private String firstPeriod;

    @Column(length = 20)
    private String secondPeriod;

    @Column(length = 20)
    private String thirdPeriod;

    @Column(length = 20)
    private String fourthPeriod;

    @Column(length = 20)
    private String fifthPeriod;

    @Column(length = 20)
    private String sixthPeriod;

    @Column(length = 20)
    private String seventhPeriod;
}
