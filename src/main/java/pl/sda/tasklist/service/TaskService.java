package pl.sda.tasklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.TaskCategoryRepository;
import pl.sda.tasklist.dao.TaskRepository;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.exception.TaskNotFoundException;
import pl.sda.tasklist.model.TaskEntity;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskCategoryRepository taskCategoryRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public void addTask(CreateTaskForm form, String categoryUrl, String username) {
        TaskEntity taskEntity = modelMapper.map(form);
        taskEntity.setUuid(UUID.randomUUID().getMostSignificantBits());
        while (taskRepository.existsByUuid(taskEntity.getUuid())) {
            taskEntity.setUuid(taskEntity.getUuid() + 1);
        }
        taskEntity.setDone(false);
        taskRepository.save(taskEntity);
    }

    public void editTask(TaskDto dto, String username) throws TaskNotFoundException {
        TaskEntity entity = taskRepository.findByUuid(dto.getUuid()).orElseThrow(() -> new TaskNotFoundException(dto.getUuid()));
        TaskEntity modifiedEntity = modelMapper.map(dto, entity, username);
        taskRepository.save(modifiedEntity);
    }
}

