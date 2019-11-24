package pl.sda.tasklist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class TaskCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<TaskEntity> tasks;

    @ManyToOne
    @JoinColumn(name = "id_task_category")
    private UserEntity user;
}
