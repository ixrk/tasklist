package pl.sda.tasklist.service;

import org.springframework.stereotype.Service;
import pl.sda.tasklist.dto.SignUpForm;
import pl.sda.tasklist.exception.ExistsUserException;

@Service
public interface SignUpService {
    void saveUser(SignUpForm signUpForm) throws ExistsUserException;

}
