package pl.sda.tasklist.exception;

public class UserExistsException extends Exception {
    public UserExistsException(String message) {
        super(message);
    }
}
