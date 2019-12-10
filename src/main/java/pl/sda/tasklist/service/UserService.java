package pl.sda.tasklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.UserRepository;
import pl.sda.tasklist.dao.UserRoleRepository;
import pl.sda.tasklist.dto.SignUpForm;
import pl.sda.tasklist.exception.UserExistsException;
import pl.sda.tasklist.model.UserEntity;
import pl.sda.tasklist.model.UserRoleEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(SignUpForm signUpForm, String roleAuthority) throws UserExistsException {
        if (userRepository.existsByUserName(signUpForm.getUserName())) {
            throw new UserExistsException("User with name " + signUpForm.getUserName() + " already exists!");
        }

        String encodedPassword = passwordEncoder.encode(signUpForm.getPassword());
        UserEntity userEntity = new UserEntity(signUpForm.getUserName(), encodedPassword, signUpForm.getBirthDate());

        UserRoleEntity userRole = userRoleRepository.findByName(roleAuthority);
        userEntity.getRoles().add(userRole);

        userRepository.save(userEntity);
    }

    public List<String> getAllUsernames() {
        return userRepository.findAll().stream()
                .map(UserEntity::getUserName)
                .collect(Collectors.toList());
    }
}
