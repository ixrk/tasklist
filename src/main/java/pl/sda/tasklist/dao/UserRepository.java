package pl.sda.tasklist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sda.tasklist.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query("from UserEntity u left join fetch u.roles where u.userName = :name" )
    Optional<UserEntity> findUserWithRolesByUserName(String name);

    Optional<UserEntity> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
