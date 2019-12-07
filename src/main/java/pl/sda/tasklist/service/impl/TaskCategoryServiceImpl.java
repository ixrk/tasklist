package pl.sda.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.TaskCategoryRepository;
import pl.sda.tasklist.dao.UserRepository;
import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.dto.TaskCategoryForm;
import pl.sda.tasklist.exception.UserExistsException;
import pl.sda.tasklist.exception.UserNotExistsException;
import pl.sda.tasklist.mapper.ModelMapper;
import pl.sda.tasklist.model.TaskCategoryEntity;
import pl.sda.tasklist.model.UserEntity;
import pl.sda.tasklist.service.TaskCategoryService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {
    private final TaskCategoryRepository taskCategoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<TaskCategoryDto> getAllTaskCategoriesByUser(String user) {
        List<TaskCategoryEntity> taskCategoryEntities = taskCategoryRepository.findAllByUser_UserName(user);
        return taskCategoryEntities.stream()
                .map(ModelMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void addTaskCategoryForUser(String user, TaskCategoryForm form) {
        TaskCategoryEntity taskCategoryEntity = new TaskCategoryEntity();
        taskCategoryEntity.setName(form.getName());
        taskCategoryEntity.setDescription(form.getDescription());
        taskCategoryEntity.setTasks(new ArrayList<>());
        UserEntity userEntity = userRepository.findByUserName(user).orElseThrow(() -> new UserNotExistsException(user + "- user does not exist"));
        taskCategoryEntity.setUser(userEntity);
        taskCategoryRepository.save(taskCategoryEntity);
    }

    @Override
    public void deleteTaskCategory(Long taskCategoryId) {
        taskCategoryRepository.deleteById(taskCategoryId);
    }
}
