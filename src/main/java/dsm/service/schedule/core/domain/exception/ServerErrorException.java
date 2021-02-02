package dsm.service.schedule.core.domain.exception;

public class ServerErrorException extends BusinessException {
    public ServerErrorException() {
        super(500, 0, "Server Error");
    }

    public ServerErrorException(String message) {
        super(500, 0, message);
    }

    public ServerErrorException(int status, String message) {
        super(status, 0, message);
    }

    public ServerErrorException(Integer StatusCode, Integer ErrorCode, String message) {
        super(StatusCode, ErrorCode, message);
    }
}