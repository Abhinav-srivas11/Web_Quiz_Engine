package engine;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//@Entity
//        @Table(name = "user")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String firstName;
//
//    @NotBlank(message = "Email is mandatory")
//    @Pattern(regexp = "\\w{3,}@[a-z]+\\.[a-z]{2,3}")
//    @Column(name = "username", nullable = false, unique = true)
//    private String email;
//    @NotBlank(message = "Password is mandatory")
//    @Size(min = 5, message = "Password must have at least five characters")
//    private String password;
//
//    @OneToMany(mappedBy = "user")
//    private List<QuizFormat> quizzes;
//
//    private boolean active;
//
//    public User() {
//
//    }
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", active=" + active +
//                ", quizzes=" + quizzes +
//                '}';
//    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "\\w{3,}@[a-z]+\\.[a-z]{2,3}")
    @Column(name = "username", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 5, message = "Password must have at least five characters")
    private String password;

    private boolean active;

    @OneToMany(mappedBy = "user")
    private List<QuizFormat> quizzes;

    protected User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<QuizFormat> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<QuizFormat> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", quizzes=" + quizzes +
                '}';
    }
}