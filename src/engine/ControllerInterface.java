package engine;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//through this interface we are extending the jpa crudrepository interface to utilized all its methods in the code
//Note : the interface is better than the class as we are not forced to implement the methods from CrudRepository interface
//as we already know that the interface will have only method declarations and not their definitions


@Repository
@Component
public interface ControllerInterface extends CrudRepository<QuizFormat, Long> {
    QuizFormat findById(long id);
    // we only need this method as we just need to know the quiz that is saved under that ID
}
