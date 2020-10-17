package YouTubeSearchApi.exception;

public class NoResultFoundException extends Exception {
    public NoResultFoundException() {
        super();
    }

    public NoResultFoundException(String message) {
        super(message);
    }

    public NoResultFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoResultFoundException(Throwable cause) {
        super(cause);
    }

    protected NoResultFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
