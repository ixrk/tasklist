package pl.sda.tasklist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
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

    public UserEntity(String userName, String password, LocalDate birthDate) {
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
    }
}
