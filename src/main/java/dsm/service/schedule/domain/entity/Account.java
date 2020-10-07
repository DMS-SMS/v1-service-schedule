package dsm.service.schedule.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Account {
    private Integer grade;

    private Integer group;

    private String name;

    private String phoneNumber;
}
