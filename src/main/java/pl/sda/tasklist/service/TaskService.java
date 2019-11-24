package pl.sda.tasklist.service;

import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto getTaskById(Long id);
    void addTask(CreateTaskForm form);
    List<TaskDto> getTasks();
    //    void deleteTaskById(Long id);

}
