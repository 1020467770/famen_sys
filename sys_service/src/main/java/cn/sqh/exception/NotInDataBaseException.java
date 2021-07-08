package cn.sqh.exception;

public class NotInDataBaseException extends RuntimeException {
    public NotInDataBaseException(String message) {
        super(message);
    }

    public NotInDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
