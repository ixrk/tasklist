package pl.sda.tasklist.mapper;

import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.model.TaskEntity;

import java.util.UUID;

public class ModelMapper {


    public static TaskDto mapTaskEntityToTaskDto(TaskEntity task) {
        return new TaskDto(task.getUuid(),task.getName(),task.getDescription(), task.isDone(),
                task.getPriority(),task.getCategory());
    }

    public static TaskDto mapFormToTaskDto(CreateTaskForm form) {
        TaskDto taskDto = new TaskDto(
                UUID.randomUUID().getMostSignificantBits(),
                form.getName(),
                form.getDescription(),
                form.isDone(),
                form.getPriority(),
                form.getCategory());
        return taskDto;
    }



}
