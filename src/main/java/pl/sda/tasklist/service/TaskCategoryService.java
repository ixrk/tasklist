package pl.sda.tasklist.service;

import pl.sda.tasklist.dto.TaskCategoryDto;

import java.util.List;

public interface TaskCategoryService {

    List<TaskCategoryDto> getAllTaskCategoriesByUser(String user);
}
