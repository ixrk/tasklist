package pl.sda.tasklist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.tasklist.model.Priority;
import pl.sda.tasklist.model.TaskCategoryEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskForm {
    private String name;
    private String description;
    private boolean isDone;
    private Priority priority;
    private TaskCategoryEntity category;
}
