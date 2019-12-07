package pl.sda.tasklist.service;

import org.springframework.stereotype.Service;
import pl.sda.tasklist.dto.SignUpForm;
import pl.sda.tasklist.exception.UserExistsException;

import java.util.List;

@Service
public interface UserService {
    void saveUser(SignUpForm signUpForm) throws UserExistsException;
    List<String> getAllUsernames();
}
