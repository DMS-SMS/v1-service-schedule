package dsm.service.schedule.infra.implementation.repository;

import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.domain.exception.ServerError;
import dsm.service.schedule.domain.repository.StudentRepository;
import dsm.service.schedule.infra.auth.AuthHandler;
import dsm.service.schedule.infra.auth.mapper.AuthMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private final AuthMapper authMapper;

    private final AuthHandler authHandler;

    @Override
    public Optional<Account> findById(String uuid) {
        try {
            return authMapper.authGetStudentInformResponseMapper(
                    authHandler.getStudentInform(
                            authMapper.authGetStudentInformRequestMapper(uuid))
            );
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }
}
