package engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
/*
Our custom repository does not contain any method as most of the
operation required already available through the JpaRepository
 */
}
