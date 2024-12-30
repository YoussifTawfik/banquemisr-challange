package banquemisr.challenge05.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Table(name = "ROLE_PERMISSIONS_TBL")
@Entity
@Data
@Builder
public class RolePermission extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "FK_ROLE_ID")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "FK_PERMISSION_ID")
    Permission permission;
}
