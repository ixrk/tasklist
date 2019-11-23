package pl.sda.tasklist.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TaskCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<TaskEntity> tasks;
}
