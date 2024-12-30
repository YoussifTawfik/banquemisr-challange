package banquemisr.challenge05.service.impl;

import banquemisr.challenge05.repository.RolePermissionRepository;
import banquemisr.challenge05.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;


    @Override
    public List<String> getPermCodesByRole(Long roleId) {
        return rolePermissionRepository.getPermissionCodesByRoleId(roleId);
    }
}
