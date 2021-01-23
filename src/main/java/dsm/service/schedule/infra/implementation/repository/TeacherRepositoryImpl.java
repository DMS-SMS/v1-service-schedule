package dsm.service.schedule.infra.implementation.repository;

import dsm.service.schedule.domain.entity.Account;
import dsm.service.schedule.domain.exception.ServerError;
import dsm.service.schedule.domain.repository.TeacherRepository;
import dsm.service.schedule.infra.auth.AuthHandler;
import dsm.service.schedule.infra.auth.mapper.AuthMapper;
import dsm.service.schedule.proto.GetTeacherInformWithUUIDResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepository {
    private final AuthHandler authHandler;

    private final AuthMapper authMapper;

    @Override
    public Optional<Account> findById(String uuid) {
        try {
            return authMapper.authGetTeacherInformResponseMapper(
                    authHandler.getTeacherInform(
                            authMapper.authGetTeacherInformRequestMapper(uuid))
            );
        } catch (InterruptedException e) {
            throw new ServerError("Auth server (Teacher) timeout (3s) on Schedule Service. Detail : \n "+ e.getMessage());
        } catch (NullPointerException e) {
            throw new ServerError(503, "Can not found service in Consul");
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }
}
