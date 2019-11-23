package pl.sda.tasklist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.tasklist.model.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}
