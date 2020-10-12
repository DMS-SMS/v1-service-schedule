package dsm.service.schedule.domain.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super(404, 1001, "NotFoundException");
    }
}
