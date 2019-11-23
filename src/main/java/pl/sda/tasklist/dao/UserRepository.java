package pl.sda.tasklist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.tasklist.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
