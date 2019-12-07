package pl.sda.tasklist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.UserRepository;
import pl.sda.tasklist.dao.UserRoleRepository;
import pl.sda.tasklist.dto.SignUpForm;
import pl.sda.tasklist.exception.UserExistsException;
import pl.sda.tasklist.model.UserEntity;
import pl.sda.tasklist.model.UserRoleEntity;
import pl.sda.tasklist.service.UserService;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(SignUpForm signUpForm) throws UserExistsException {
        if (userRepository.existsByUserName(signUpForm.getUserName())) {
            throw new UserExistsException("User with name " + signUpForm.getUserName() + " already exists!");
        }

        String encodedPassword = passwordEncoder.encode(signUpForm.getPassword());
        UserEntity userEntity = new UserEntity(signUpForm.getUserName(), encodedPassword, signUpForm.getBirthDate());

        UserRoleEntity userRole = userRoleRepository.findByName("ROLE_USER");
        userEntity.getRoles().add(userRole);

        userRepository.save(userEntity);
    }
}
