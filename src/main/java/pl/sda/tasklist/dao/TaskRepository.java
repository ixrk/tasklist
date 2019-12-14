package pl.sda.tasklist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.tasklist.model.TaskEntity;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findByUuid(long uuid);
}
