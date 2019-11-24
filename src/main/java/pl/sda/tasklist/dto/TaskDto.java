package pl.sda.tasklist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import pl.sda.tasklist.model.Priority;
import pl.sda.tasklist.model.TaskCategoryEntity;

@AllArgsConstructor
@Data
public class TaskDto {

    private String uuid;
    private String name;
    private String description;
    private boolean isDone;
    private Priority priority;
    private TaskCategoryEntity category;

    public TaskDto(long mostSignificantBits, String name, String description, boolean done, Priority priority, TaskCategoryEntity category) {


    }
}
