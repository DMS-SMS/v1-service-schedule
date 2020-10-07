package dsm.service.schedule.infra.auth.mapper;

import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.proto.GetTeacherInformWithUUIDRequest;
import dsm.service.schedule.proto.GetTeacherInformWithUUIDResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class AuthMapper {
    public GetTeacherInformWithUUIDRequest authGetInformRequestMapper(String uuid) {
        return GetTeacherInformWithUUIDRequest.newBuilder()
                .setTeacherUUID(uuid)
                .setUUID(uuid)
                .build();
    }

    public Optional<Account> authGetInformResponseMapper(GetTeacherInformWithUUIDResponse response) {
        if (response.getStatus() != 200) {
            return Optional.empty();
        }

        return Optional.of(Account.builder()
                .grade(response.getGrade())
                .group(response.getGroup())
                .name(response.getName())
                .phoneNumber(response.getPhoneNumber())
                .build());
    }
}
