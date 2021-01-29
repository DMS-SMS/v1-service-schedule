package dsm.service.schedule.core.domain.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Integer StatusCode;
    private final Integer ErrorCode;
    private final String message;

    public BusinessException(Integer StatusCode, Integer ErrorCode, String message) {
        super(message);
        this.StatusCode = StatusCode;
        this.ErrorCode = ErrorCode;
        this.message = message;
    }
}