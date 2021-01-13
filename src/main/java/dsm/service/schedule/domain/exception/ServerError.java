package dsm.service.schedule.domain.exception;

public class ServerError extends BusinessException {
    public ServerError() {
        super(500, -1000, "Server Error");
    }

    public ServerError(String message) {
        super(500, -1000, message);
    }

    public ServerError(int status, String message) {
        super(status, -1000, message);
    }

    public ServerError(Integer StatusCode, Integer ErrorCode, String message) {
        super(StatusCode, ErrorCode, message);
    }
}
