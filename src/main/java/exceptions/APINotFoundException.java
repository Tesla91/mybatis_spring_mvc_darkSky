package exceptions;

public class APINotFoundException extends Exception {

    public APINotFoundException(String msg) {
        super(msg);
    }

    public APINotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
