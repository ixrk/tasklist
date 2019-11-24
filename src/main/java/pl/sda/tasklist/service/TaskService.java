package pl.sda.tasklist.service;

import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto getTaskById(Long id);
    void addTask(CreateTaskForm form);
    void deleteTaskById(Long id);
    List<TaskDto> getTasks();


}
