package pl.sda.tasklist.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.TaskCategoryRepository;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.model.TaskCategoryEntity;
import pl.sda.tasklist.model.TaskEntity;
import pl.sda.tasklist.service.TaskCategoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ModelMapper {

    private final TaskCategoryRepository taskCategoryRepository;

    public TaskEntity map(CreateTaskForm form) {
        TaskEntity entity = new TaskEntity();
        entity.setName(form.getName());
        entity.setDescription(form.getDescription());
        entity.setPriority(form.getPriority());
        return entity;
    }

    public TaskDto map(TaskEntity entity) {
        TaskDto dto = new TaskDto();
        dto.setUuid(entity.getUuid());
        dto.setUuidHex(Long.toHexString(entity.getUuid()));
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDone(entity.isDone());
        dto.setPriority(entity.getPriority());
        dto.setCategory(map(entity.getCategory()));
        return dto;
    }

    public TaskEntity map(TaskDto dto, TaskEntity baseEntity, String username) {
        TaskEntity entity = new TaskEntity();
        entity.setId(baseEntity.getId());

        entity.setUuid(dto.getUuid());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDone(dto.isDone());
        entity.setPriority(dto.getPriority());
        entity.setCategory(taskCategoryRepository.findByUrlNameAndUser_UserName(dto.getCategory().getUrlName(), username).get());
        return entity;
    }

    public TaskCategoryDto map(TaskCategoryEntity categoryEntity) {
        TaskCategoryDto categoryDto = new TaskCategoryDto();
        categoryDto.setName(categoryEntity.getName());
        categoryDto.setUrlName(categoryEntity.getUrlName());
        categoryDto.setDescription(categoryEntity.getDescription());
        List<TaskDto> taskDtos = categoryEntity.getTasks().stream()
                .map(this::map)
                .collect(Collectors.toList());
        categoryDto.setTasks(taskDtos);
        return categoryDto;
    }
}
