package dsm.service.schedule.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Table(indexes = {@Index(columnList = "target_grade"), @Index(columnList = "target_group")})
@Entity(name="tbl_timetable")
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
    private String day;

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
