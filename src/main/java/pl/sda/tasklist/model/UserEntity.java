package pl.sda.tasklist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private LocalDate birthDate;

    @ManyToMany
    private Set<UserRoleEntity> roles = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "id_user")
    private List<TaskCategoryEntity> categories;

}
