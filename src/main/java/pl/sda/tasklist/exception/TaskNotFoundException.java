package pl.sda.tasklist.exception;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends Exception {
    private String uuid;

    public TaskNotFoundException(String uuid) {
        super(uuid + " - task not found");
        this.uuid = uuid;
    }
}
