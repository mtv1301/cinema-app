package cinema.dto.request;

import cinema.lib.FieldsValueMatch;
import cinema.lib.ValidEmail;
import cinema.model.Role;
import java.util.Set;
import javax.validation.constraints.Size;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
