package pl.sda.tasklist.model;

import lombok.*;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean isDone;
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "id_task")
    private TaskCategoryEntity category;

}
