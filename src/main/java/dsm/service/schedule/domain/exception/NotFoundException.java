package dsm.service.schedule.domain.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super(404, 1001, "NotFoundException");
    }

    public NotFoundException(String message) {
        super(404, 1001, message);
    }
}
