package banquemisr.challenge05.repository;

import banquemisr.challenge05.entity.Permission;
import banquemisr.challenge05.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
