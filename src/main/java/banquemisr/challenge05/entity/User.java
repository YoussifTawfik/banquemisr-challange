package banquemisr.challenge05.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
