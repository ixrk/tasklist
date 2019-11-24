package pl.sda.tasklist.mock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.UserRoleRepository;
import pl.sda.tasklist.dto.SignInForm;
import pl.sda.tasklist.dto.SignUpForm;
import pl.sda.tasklist.dto.TaskCategoryForm;
import pl.sda.tasklist.exception.UserExistsException;
import pl.sda.tasklist.model.UserRoleEntity;
import pl.sda.tasklist.service.SignUpService;
import pl.sda.tasklist.service.TaskCategoryService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class Mocker {

    private final UserRoleRepository userRoleRepository;
    private final SignUpService signUpService;
    private final TaskCategoryService taskCategoryService;

    @PostConstruct
    public void populateRoles() {
        UserRoleEntity ordinaryUserRole = new UserRoleEntity("ROLE_USER");
        userRoleRepository.save(ordinaryUserRole);

        UserRoleEntity adminUserRole = new UserRoleEntity("ROLE_ADMIN");
        userRoleRepository.save(adminUserRole);
    }

    @PostConstruct
    public void mockUser() throws UserExistsException {
        final String username = "user1";
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setUserName(username);
        signUpForm.setBirthDate(LocalDate.now());
        signUpForm.setPassword("Password1");
        signUpService.saveUser(signUpForm);

        TaskCategoryForm taskCategoryForm1 = new TaskCategoryForm();
        taskCategoryForm1.setName("Shopping list");
        taskCategoryForm1.setDescription("This week's shopping list");
        taskCategoryService.addTaskCategoryForUser(username, taskCategoryForm1);

        TaskCategoryForm taskCategoryForm2 = new TaskCategoryForm();
        taskCategoryForm2.setName("Work checklist");
        taskCategoryForm2.setDescription("Things to do before leaving");
        taskCategoryService.addTaskCategoryForUser(username, taskCategoryForm2);
    }
}
