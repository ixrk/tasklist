package pl.sda.tasklist.mapper;

import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.model.TaskCategoryEntity;
import pl.sda.tasklist.model.TaskEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper {

    }
    public TaskDto map(TaskEntity task){
        return new TaskDto(task.getUuid(),task.getName(),task.getDescription(), task.isDone(),
                task.getPriority(),task.getCategory());
    }

    public static TaskCategoryDto map(TaskCategoryEntity categoryEntity) {
        TaskCategoryDto categoryDto = new TaskCategoryDto();
        categoryDto.setName(categoryEntity.getName());
        categoryDto.setDescription(categoryEntity.getDescription());
        List<TaskDto> taskDtos = categoryEntity.getTasks().stream()
                .map(ModelMapper::map)
                .collect(Collectors.toList());
        categoryDto.setTasks(taskDtos);
    }
}
