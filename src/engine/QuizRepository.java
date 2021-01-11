package engine;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<QuizFormat, Long> {
    List<QuizFormat> findAll();
}
