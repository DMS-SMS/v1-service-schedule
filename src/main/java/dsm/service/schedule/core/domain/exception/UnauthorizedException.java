package dsm.service.schedule.core.domain.exception;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() {
        super(401, 0, "UnAuthorized");
    }
}