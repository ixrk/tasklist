package pl.sda.tasklist.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
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
    @JoinColumn(name = "id_category")
    private CategoryEntity category;

}
