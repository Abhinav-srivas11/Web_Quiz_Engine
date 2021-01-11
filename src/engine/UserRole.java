package engine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "users_roles")
public class UserRole implements Serializable {

    @Pattern(regexp = "\\w{3,}@[a-z]+\\.[a-z]{2,3}")
    @Id
    @Column(name = "username")
    private String email;

    @Column(name = "role")
    private String role;

    public UserRole() { }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
