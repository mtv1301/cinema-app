package cinema.service;

import cinema.model.Role;
import java.util.Optional;

public interface RoleService {
    void add(Role role);

    Optional<Role> getRoleByName(String roleName);
}
