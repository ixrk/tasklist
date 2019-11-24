package pl.sda.tasklist.mock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.tasklist.dao.UserRoleRepository;
import pl.sda.tasklist.model.UserRoleEntity;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Service
public class Mocker {

    private final UserRoleRepository userRoleRepository;

    @PostConstruct
    public void populateRoles() {
        UserRoleEntity ordinaryUserRole = new UserRoleEntity("ROLE_USER");
        userRoleRepository.save(ordinaryUserRole);

        UserRoleEntity adminUserRole = new UserRoleEntity("ROLE_ADMIN");
        userRoleRepository.save(adminUserRole);
    }
}
