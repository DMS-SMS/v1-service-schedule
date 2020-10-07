package dsm.service.schedule.domain.exception;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private Integer StatusCode;
    private Integer ErrorCode;
    private String message;

    public BusinessException(Integer StatusCode, Integer ErrorCode, String message) {
        super(message);
        this.StatusCode = StatusCode;
        this.ErrorCode = ErrorCode;
        this.message = message;
    }
}
