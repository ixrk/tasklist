package pl.sda.tasklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.TaskCategoryRepository;
import pl.sda.tasklist.dao.UserRepository;
import pl.sda.tasklist.dto.TaskCategoryDto;
import pl.sda.tasklist.dto.TaskCategoryForm;
import pl.sda.tasklist.exception.TaskCategoryNotFoundException;
import pl.sda.tasklist.exception.UserNotFoundException;
import pl.sda.tasklist.model.TaskCategoryEntity;
import pl.sda.tasklist.model.UserEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class TaskCategoryService {
    private final TaskCategoryRepository taskCategoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public TaskCategoryDto getCategoryByUrlNameAndUsername(String urlName, String username) throws TaskCategoryNotFoundException {
        TaskCategoryEntity entity = taskCategoryRepository.findByUrlNameAndUser_UserName(urlName, username).orElseThrow(() -> new TaskCategoryNotFoundException(urlName));
        return modelMapper.map(entity);
    }

    public List<TaskCategoryDto> getAllTaskCategoriesByUser(String user) throws UserNotFoundException {
        if (!userRepository.existsByUserName(user)) {
            throw new UserNotFoundException(user);
        }

        List<TaskCategoryEntity> taskCategoryEntities = taskCategoryRepository.findAllByUser_UserName(user);
        return taskCategoryEntities.stream()
                .map(modelMapper::map)
                .collect(Collectors.toList());
    }

    public void addTaskCategoryForUser(String user, TaskCategoryForm form) throws UserNotFoundException {
        TaskCategoryEntity taskCategoryEntity = new TaskCategoryEntity();
        taskCategoryEntity.setName(form.getName());
        taskCategoryEntity.setUrlName(form.getName().replace(" ", "-"));
        taskCategoryEntity.setDescription(form.getDescription());
        taskCategoryEntity.setTasks(new ArrayList<>());
        UserEntity userEntity = userRepository.findByUserName(user).orElseThrow(() -> new UserNotFoundException(user));
        taskCategoryEntity.setUser(userEntity);
        taskCategoryRepository.save(taskCategoryEntity);
    }

    public void deleteTaskCategory(String username, String urlName) {
        taskCategoryRepository.deleteByUser_UserNameAndUrlName(username, urlName);
    }
}
