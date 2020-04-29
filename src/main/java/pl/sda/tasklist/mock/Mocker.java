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
        createTaskForm3.setName("Milk");
        createTaskForm3.setDescription("UHT 3.2%");
        createTaskForm3.setPriority(Priority.MEDIUM);
        createTaskForm3.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm3, urlName, username);

        CreateTaskForm createTaskForm4 = new CreateTaskForm();
        createTaskForm4.setName("Bell pepper");
        createTaskForm4.setDescription("2x green");
        createTaskForm4.setPriority(Priority.MEDIUM);
        createTaskForm4.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm4, urlName, username);

        TaskCategoryForm taskCategoryForm2 = new TaskCategoryForm();
        taskCategoryForm2.setName("Work checklist");
        taskCategoryForm2.setDescription("Things to do before leaving");
        taskCategoryService.addTaskCategoryForUser(username, taskCategoryForm2);

        urlName = taskCategoryService.getAllTaskCategoriesByUser(username).get(1).getUrlName();
        CreateTaskForm createTaskForm5 = new CreateTaskForm();
        createTaskForm5.setName("Clean the workshop");
        createTaskForm5.setPriority(Priority.MEDIUM);
        createTaskForm5.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm5, urlName, username);

        CreateTaskForm createTaskForm6 = new CreateTaskForm();
        createTaskForm6.setName("Switch off the lights");
        createTaskForm6.setPriority(Priority.MEDIUM);
        createTaskForm6.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm6, urlName, username);

        CreateTaskForm createTaskForm7 = new CreateTaskForm();
        createTaskForm7.setName("Close doors");
        createTaskForm7.setPriority(Priority.MEDIUM);
        createTaskForm7.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm7, urlName, username);

        CreateTaskForm createTaskForm8 = new CreateTaskForm();
        createTaskForm8.setName("wash the tools");
        createTaskForm8.setPriority(Priority.MEDIUM);
        createTaskForm8.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm8, urlName, username);

        CreateTaskForm createTaskForm9 = new CreateTaskForm();
        createTaskForm9.setName("Say goodbye");
        createTaskForm9.setPriority(Priority.MEDIUM);
        createTaskForm9.setCategoryUrl(urlName);
        taskService.addTask(createTaskForm9, urlName, username);

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
