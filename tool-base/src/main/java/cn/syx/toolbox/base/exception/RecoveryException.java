package cn.syx.toolbox.base.exception;

public class RecoveryException extends RuntimeException {

    public RecoveryException() {
        super();
    }

    public RecoveryException(String message) {
        super(message);
    }

    public RecoveryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecoveryException(Throwable cause) {
        super(cause);
    }

    protected RecoveryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
