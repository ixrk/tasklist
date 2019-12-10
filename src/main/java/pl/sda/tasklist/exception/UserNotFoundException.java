package pl.sda.tasklist.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends Exception {
    private final String username;

    public UserNotFoundException(String username) {
        super(username + "- user not found");
        this.username = username;
    }
}
