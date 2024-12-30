package banquemisr.challenge05.security;

import banquemisr.challenge05.exception.base.ExceptionFactory;
import banquemisr.challenge05.repository.RolePermissionRepository;
import banquemisr.challenge05.repository.UserRepository;
import banquemisr.challenge05.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RolePermissionService rolePermissionService;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        banquemisr.challenge05.entity.User user = userRepository.findByEmail(username).orElseThrow(ExceptionFactory::throwNotFoundException);
        List<String> permissionsCodes= rolePermissionService.getPermCodesByRole(user.getRole().getId());
        Collection<SimpleGrantedAuthority> authorities = permissionsCodes.stream().map(SimpleGrantedAuthority::new).toList();
        return new User(user.getEmail(), user.getPassword(), authorities);

    }
}
