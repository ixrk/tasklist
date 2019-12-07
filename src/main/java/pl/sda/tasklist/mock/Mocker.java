package pl.sda.tasklist.mock;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.UserRepository;
import pl.sda.tasklist.dto.SignUpForm;
import pl.sda.tasklist.dto.TaskCategoryForm;
import pl.sda.tasklist.exception.UserExistsException;
import pl.sda.tasklist.service.UserService;
import pl.sda.tasklist.service.TaskCategoryService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class Mocker {

    private final UserRepository userRepository;
    private final UserService userService;
    private final TaskCategoryService taskCategoryService;

    @PostConstruct
    public void mockUser() throws UserExistsException {
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
