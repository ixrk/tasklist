package pl.sda.tasklist.dto;

import lombok.AllArgsConstructor;
import pl.sda.tasklist.model.Priority;
import pl.sda.tasklist.model.TaskCategoryEntity;

import javax.persistence.*;
import java.util.UUID;
@AllArgsConstructor
public class TaskDto {

    private String uuid;
    private String name;
    private String description;
    private boolean isDone;
    private Priority priority;
    private TaskCategoryEntity category;
}
