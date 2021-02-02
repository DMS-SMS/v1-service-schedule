package dsm.service.schedule.data.adapter.repository.mapper;

import dsm.service.schedule.core.domain.entity.Account;
import dsm.service.schedule.core.domain.entity.enums.AccountType;
import dsm.service.schedule.proto.GetStudentInformWithUUIDResponse;
import dsm.service.schedule.proto.GetTeacherInformWithUUIDResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepositoryMapper {
    public Optional<Account> map(GetStudentInformWithUUIDResponse proto) {
        if (proto == null) return Optional.empty();
        return Optional.of(
                new Account(
                        proto.getGrade(),
                        proto.getGroup(),
                        proto.getName(),
                        proto.getPhoneNumber(),
                        AccountType.STUDENT
                )
        );
    }

    public Optional<Account> map(GetTeacherInformWithUUIDResponse proto) {
        if (proto == null) return Optional.empty();
        return Optional.of(
                new Account(
                        proto.getGrade(),
                        proto.getGroup(),
                        proto.getName(),
                        proto.getPhoneNumber(),
                        AccountType.TEACHER
                )
        );
    }
}
