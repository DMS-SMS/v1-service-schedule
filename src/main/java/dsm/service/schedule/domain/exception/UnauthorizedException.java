package dsm.service.schedule.domain.exception;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() {
        super(401, 1001, "UnAuthorized");
    }
}
