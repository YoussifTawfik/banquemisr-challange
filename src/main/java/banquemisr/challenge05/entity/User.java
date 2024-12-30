package banquemisr.challenge05.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "USERS_TBL")
@Entity
@Data
public class User extends BaseEntity{

    @Column(name = "UUID", nullable = false, unique = true)
    private String uuid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "FK_ROLE_ID")
    private Role role;
}
