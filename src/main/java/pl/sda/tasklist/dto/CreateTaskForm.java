package pl.sda.tasklist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.tasklist.model.Priority;
import pl.sda.tasklist.model.TaskCategoryEntity;

@Data
public class CreateTaskForm {
    private String name;
    private String description;
    private Priority priority;
    private String categoryUrl;
}
