package pl.sda.tasklist.exception;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends Exception {
    private long uuid;

    public TaskNotFoundException(long uuid) {
        super(uuid + " - task not found");
        this.uuid = uuid;
    }
}
