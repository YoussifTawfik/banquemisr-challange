package banquemisr.challenge05.repository;

import banquemisr.challenge05.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    @Query("Select p.code from RolePermission rp join rp.role r join rp.permission p where r.id=:roleId")
    List<String> getPermissionCodesByRoleId(Long roleId);
}
