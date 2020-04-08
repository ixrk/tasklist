package pl.sda.tasklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.TaskCategoryRepository;
import pl.sda.tasklist.dao.TaskRepository;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.exception.TaskNotFoundException;
import pl.sda.tasklist.model.TaskCategoryEntity;
import pl.sda.tasklist.model.TaskEntity;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
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

        TaskCategoryEntity category = taskCategoryRepository.findByUrlNameAndUser_UserName(categoryUrl, username).get();
        category.getTasks().add(taskEntity);

        taskCategoryRepository.save(category);
    }

    public CreateTaskForm getTaskAsForm(String uuidHex) throws TaskNotFoundException {
        TaskEntity entity = getTaskEntityByUuidHex(uuidHex);
        CreateTaskForm taskForm = new CreateTaskForm();
        taskForm.setName(entity.getName());
        taskForm.setDescription(entity.getDescription());
        taskForm.setPriority(entity.getPriority());
        return taskForm;
    }


    public void editTask(CreateTaskForm taskForm, String uuidHex) throws TaskNotFoundException {
        TaskEntity entity = getTaskEntityByUuidHex(uuidHex);
        entity.setName(taskForm.getName());
        entity.setDescription(taskForm.getDescription());
        entity.setPriority(taskForm.getPriority());
        taskRepository.save(entity);
    }

    public void switchDone(String uuidHex) throws TaskNotFoundException {
        TaskEntity taskEntity = getTaskEntityByUuidHex(uuidHex);
        if (taskEntity.isDone()) {
            taskEntity.setDone(false);
        } else {
            taskEntity.setDone(true);
        }
    }

    public void deleteTask(String uuidHex) {
        long uuid = Long.parseLong(uuidHex, 32);
        taskRepository.deleteByUuid(uuid);
    }

    private TaskEntity getTaskEntityByUuidHex(String uuidHex) throws TaskNotFoundException {
        long uuid = Long.parseLong(uuidHex, 32);
        return taskRepository.findByUuid(uuid).orElseThrow(() -> new TaskNotFoundException(uuid));
    }
}

