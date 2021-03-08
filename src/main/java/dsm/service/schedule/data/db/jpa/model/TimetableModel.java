package dsm.service.schedule.data.db.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@Table(indexes = {@Index(columnList = "targetGrade"), @Index(columnList = "targetGroup")})
@Entity(name="tbl_timetable")
@NoArgsConstructor
@AllArgsConstructor
public class TimetableModel {
    @Id
    private String uuid;

    @Column(nullable = false)
    private Integer targetGrade;

    @Column(nullable = false)
    private Integer targetGroup;

    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 20)
    private String firstSubject;

    @Column(length = 20)
    private String secondSubject;

    @Column(length = 20)
    private String thirdSubject;

    @Column(length = 20)
    private String fourthSubject;

    @Column(length = 20)
    private String fifthSubject;

    @Column(length = 20)
    private String sixthSubject;

    @Column(length = 20)
    private String seventhSubject;
}