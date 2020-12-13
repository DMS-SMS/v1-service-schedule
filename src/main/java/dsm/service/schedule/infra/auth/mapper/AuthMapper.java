package dsm.service.schedule.infra.auth.mapper;

import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.domain.exception.NotFoundException;
import dsm.service.schedule.proto.GetStudentInformWithUUIDRequest;
import dsm.service.schedule.proto.GetStudentInformWithUUIDResponse;
import dsm.service.schedule.proto.GetTeacherInformWithUUIDRequest;
import dsm.service.schedule.proto.GetTeacherInformWithUUIDResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class AuthMapper {
    public GetTeacherInformWithUUIDRequest authGetTeacherInformRequestMapper(String uuid) {
        return GetTeacherInformWithUUIDRequest.newBuilder()
                .setTeacherUUID(uuid)
                .setUUID(uuid)
                .build();
    }

    public Optional<Account> authGetTeacherInformResponseMapper(GetTeacherInformWithUUIDResponse response) {
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

    public GetStudentInformWithUUIDRequest authGetStudentInformRequestMapper(String uuid) {
        return GetStudentInformWithUUIDRequest.newBuilder()
                .setStudentUUID(uuid)
                .setUUID(uuid)
                .build();
    }

    public Optional<Account> authGetStudentInformResponseMapper(GetStudentInformWithUUIDResponse response) {
        if (response.getStatus() != 200) {
            throw new NotFoundException(response.getMessage());
//            return Optional.empty();
        }

        return Optional.of(Account.builder()
                .grade(response.getGrade())
                .group(response.getGroup())
                .name(response.getName())
                .phoneNumber(response.getPhoneNumber())
                .build());
    }
}
