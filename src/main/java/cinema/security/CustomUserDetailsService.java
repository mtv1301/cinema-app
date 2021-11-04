package cinema.security;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByEmail(userName);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Can't found this user " + userOptional);
        }
        org.springframework.security.core.userdetails.User.UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(userName);
        builder.password(userOptional.get().getPassword());
        builder.roles(userOptional.get().getRoles()
                .stream()
                .map(Role::getName)
                .toArray(String[]::new));
        return builder.build();
    }
}
