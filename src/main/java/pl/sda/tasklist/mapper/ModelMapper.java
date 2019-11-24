package pl.sda.tasklist.mapper;

import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.model.TaskCategoryEntity;
import pl.sda.tasklist.model.TaskEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ModelMapper {


    public static TaskDto map(TaskEntity task) {
        return new TaskDto(task.getUuid(),task.getName(),task.getDescription(), task.isDone(),
                task.getPriority(),task.getCategory());
    }

    public static TaskDto map(CreateTaskForm form) {
        TaskDto taskDto = new TaskDto();
        taskDto.setUuid(Long.toHexString(UUID.randomUUID().getMostSignificantBits()));
        taskDto.setName(form.getName());
        taskDto.setDescription(form.getDescription());
        taskDto.setDone(form.isDone());
        taskDto.setPriority(form.getPriority());
        taskDto.setCategory(form.getCategory());

        return taskDto;
    }



    public static TaskCategoryDto map(TaskCategoryEntity categoryEntity) {
        TaskCategoryDto categoryDto = new TaskCategoryDto();
        categoryDto.setName(categoryEntity.getName());
        categoryDto.setDescription(categoryEntity.getDescription());
        List<TaskDto> taskDtos = categoryEntity.getTasks().stream()
                .map(ModelMapper::map)
                .collect(Collectors.toList());
        categoryDto.setTasks(taskDtos);
        return categoryDto;
    }
}
