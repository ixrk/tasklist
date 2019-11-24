package pl.sda.tasklist.service;

import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.dto.TaskCategoryForm;

import java.util.List;

public interface TaskCategoryService {

    List<TaskCategoryDto> getAllTaskCategoriesByUser(String user);
    void addTaskCategoryForUser(String user, TaskCategoryForm form);
}
