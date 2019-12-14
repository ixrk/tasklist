package pl.sda.tasklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.TaskCategoryRepository;
import pl.sda.tasklist.dao.TaskRepository;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.mapper.ModelMapper;
import pl.sda.tasklist.model.TaskEntity;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskCategoryRepository taskCategoryRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    //task dto
    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(modelMapper::map).get();
    }

    public void addTask(CreateTaskForm form, String categoryUrl) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName(form.getName());
        taskEntity.setDescription(form.getDescription());
        taskEntity.setDone(false);
        taskEntity.setPriority(form.getPriority());
        taskEntity.setCategory(taskCategoryRepository.findByUrlName(categoryUrl).get());
        taskRepository.save(taskEntity);
    }

    public List<TaskDto> getTasks() {
        return taskRepository
                .findAll()
                .stream()
                .map(modelMapper::map)
                .collect(Collectors.toList());
    }

    //task dto
//    public void deleteTaskById(Long id) {
//        Optional<TaskEntity> taskById = taskRepository.findById(id);
//        TaskEntity taskEntity = null;
//        if (taskById.isPresent()) {
//            taskEntity = new TaskEntity(taskById.get());
//        }
//        taskRepository.delete(taskEntity);
//
//    }
//
}

