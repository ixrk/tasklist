package pl.sda.tasklist.exception;

import lombok.Getter;

@Getter
public class TaskCategoryNotFoundException extends Exception {
    private final String urlName;

    public TaskCategoryNotFoundException(String urlName) {
        super(urlName + "- category not found");
        this.urlName = urlName;
    }
}
