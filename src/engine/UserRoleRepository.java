package engine;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}
