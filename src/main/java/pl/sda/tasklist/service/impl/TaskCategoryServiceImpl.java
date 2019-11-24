package pl.sda.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.TaskCategoryRepository;
import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.mapper.ModelMapper;
import pl.sda.tasklist.model.TaskCategoryEntity;
import pl.sda.tasklist.service.TaskCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {
    private final TaskCategoryRepository taskCategoryRepository;

    @Override
    public List<TaskCategoryDto> getAllTaskCategoriesByUser(String user) {
        List<TaskCategoryEntity> taskCategoryEntities = taskCategoryRepository.findAllByUser_UserName(user);
        return taskCategoryEntities.stream()
                .map(ModelMapper::map)
                .collect(Collectors.toList());
    }
}
