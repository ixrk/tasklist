package pl.sda.tasklist.mock;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.tasklist.dao.UserRepository;
import pl.sda.tasklist.dto.CreateTaskForm;
import pl.sda.tasklist.dto.SignUpForm;
import pl.sda.tasklist.dto.TaskCategoryForm;
import pl.sda.tasklist.exception.UserExistsException;
import pl.sda.tasklist.exception.UserNotFoundException;
import pl.sda.tasklist.model.Priority;
import pl.sda.tasklist.service.TaskService;
import pl.sda.tasklist.service.UserService;
import pl.sda.tasklist.service.TaskCategoryService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Transactional
@RequiredArgsConstructor
@Service
public class Mocker {

    private final UserRepository userRepository;
    private final UserService userService;
    private final TaskCategoryService taskCategoryService;
    private final TaskService taskService;

    @PostConstruct
    public void mockUser() throws UserExistsException, UserNotFoundException {
        final String username = "user1";

        if (userRepository.existsByUserName(username)) {
            return;
        }

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setUserName(username);
        signUpForm.setBirthDate(LocalDate.now());
        signUpForm.setPassword("Password1");
        userService.saveUser(signUpForm, "ROLE_USER");

        TaskCategoryForm taskCategoryForm1 = new TaskCategoryForm();
        taskCategoryForm1.setName("Shopping list");
        taskCategoryForm1.setDescription("This week's shopping list");
        taskCategoryService.addTaskCategoryForUser(username, taskCategoryForm1);

        TaskCategoryForm taskCategoryForm2 = new TaskCategoryForm();
        taskCategoryForm2.setName("Work checklist");
        taskCategoryForm2.setDescription("Things to do before leaving");
        taskCategoryService.addTaskCategoryForUser(username, taskCategoryForm2);

        String urlName = taskCategoryService.getAllTaskCategoriesByUser(username).get(0).getUrlName();
        CreateTaskForm createTaskForm1 = new CreateTaskForm();
        createTaskForm1.setName("Flour");
        createTaskForm1.setDescription("Type 550");
        createTaskForm1.setPriority(Priority.MEDIUM);
        createTaskForm1.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm1, urlName, username);

        CreateTaskForm createTaskForm2 = new CreateTaskForm();
        createTaskForm2.setName("Cereal");
        createTaskForm2.setDescription("Corn Flakes");
        createTaskForm2.setPriority(Priority.MEDIUM);
        createTaskForm2.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm2, urlName, username);

        CreateTaskForm createTaskForm3 = new CreateTaskForm();
        createTaskForm3.setName("Cereal");
        createTaskForm3.setDescription("Corn Flakes");
        createTaskForm3.setPriority(Priority.MEDIUM);
        createTaskForm3.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm3, urlName, username);

        CreateTaskForm createTaskForm4 = new CreateTaskForm();
        createTaskForm4.setName("Cereal");
        createTaskForm4.setDescription("Corn Flakes");
        createTaskForm4.setPriority(Priority.MEDIUM);
        createTaskForm4.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm4, urlName, username);


    }

    @PostConstruct
    public void mockAdmin() throws UserExistsException {
        final String username = "admin1";

        if (userRepository.existsByUserName(username)) {
            return;
        }

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setUserName(username);
        signUpForm.setBirthDate(LocalDate.now());
        signUpForm.setPassword("Password1");
        userService.saveUser(signUpForm, "ROLE_ADMIN");
    }
}
