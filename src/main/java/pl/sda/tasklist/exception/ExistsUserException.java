package pl.sda.tasklist.exception;

public class ExistsUserException extends Exception {
    public ExistsUserException(String message) {
        super(message);
    }
}
