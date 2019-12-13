package pl.sda.tasklist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.sda.tasklist.model.Priority;
import pl.sda.tasklist.model.TaskCategoryEntity;

@Data
public class TaskDto {

    private String uuid;
    private String name;
    private String description;
    private boolean isDone;
    private Priority priority;
    private TaskCategoryDto category;
}
