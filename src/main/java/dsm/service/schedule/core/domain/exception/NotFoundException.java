package dsm.service.schedule.core.domain.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super(404, 0, "Not Found");
    }
}
