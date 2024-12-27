package banquemisr.challenge05.service;


import banquemisr.challenge05.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {

    List<String> getPermCodesByRole(Long roleId);
}
