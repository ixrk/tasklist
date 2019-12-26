package pl.sda.tasklist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.tasklist.model.TaskCategoryEntity;

import java.util.List;
import java.util.Optional;

public interface TaskCategoryRepository extends JpaRepository<TaskCategoryEntity, Long> {
    List<TaskCategoryEntity> findAllByUser_UserName(String username);
    Optional<TaskCategoryEntity> findByUrlNameAndUser_UserName(String urlName, String username);
    boolean existsByUrlNameAndUser_UserName(String urlName, String username);
    void deleteByUser_UserNameAndUrlName(String username, String urlName);
}
