package dsm.service.schedule.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TimeTable {
    @Column
    private int grade;

    @Column
    private int group;

    @Column
    private int week;

    @Column(length = 20)
    private String time1;

    @Column(length = 20)
    private String time2;

    @Column(length = 20)
    private String time3;

    @Column(length = 20)
    private String time4;

    @Column(length = 20)
    private String time5;

    @Column(length = 20)
    private String time6;

    @Column(length = 20)
    private String time7;
}
