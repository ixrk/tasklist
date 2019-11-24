package pl.sda.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.TaskRepository;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskDto;
import pl.sda.tasklist.mapper.ModelMapper;
import pl.sda.tasklist.model.TaskEntity;
import pl.sda.tasklist.service.TaskService;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    //task dto
    @Override
    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(ModelMapper::map).get();
    }

    @Override
    public void addTask(CreateTaskForm form) {
        TaskDto taskDto = ModelMapper.map(form);
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName(taskDto.getName());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setDone(taskDto.isDone());
        taskEntity.setPriority(taskDto.getPriority());
        taskEntity.setCategory(taskDto.getCategory());
        taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskDto> getTasks() {
        List<TaskDto> listOfTaskDto = taskRepository
                .findAll()
                .stream()
                .map(ModelMapper::map)
                .collect(Collectors.toList());
        return listOfTaskDto;
    }

    //task dto
//    @Override
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

