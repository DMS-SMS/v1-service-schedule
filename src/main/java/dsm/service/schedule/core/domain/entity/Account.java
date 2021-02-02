package dsm.service.schedule.core.domain.entity;

import dsm.service.schedule.core.domain.entity.enums.AccountType;
import lombok.Value;

@Value
public class Account {
    Integer grade;

    Integer group;

    String name;

    String phoneNumber;

    AccountType type;
}
