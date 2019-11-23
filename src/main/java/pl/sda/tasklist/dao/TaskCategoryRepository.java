package pl.sda.tasklist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.tasklist.model.TaskCategoryEntity;

public interface TaskCategoryRepository extends JpaRepository<Long, TaskCategoryEntity> {

}
