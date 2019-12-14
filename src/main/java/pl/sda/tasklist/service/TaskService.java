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

    public void addTask(CreateTaskForm form, String categoryUrl) {
        TaskEntity taskEntity = modelMapper.map(form);
        taskEntity.setDone(false);
        taskEntity.setCategory(taskCategoryRepository.findByUrlName(categoryUrl).get());
        taskRepository.save(taskEntity);
    }
}

