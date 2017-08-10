package exceptions;

public class DBNotFoundException extends Exception {

    public DBNotFoundException(String msg) {
        super(msg);
    }

    public DBNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

}
