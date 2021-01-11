package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


@ControllerAdvice
@ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
class QuizException extends RuntimeException {
    String reason="Not found";

    public QuizException() {}
    public QuizException(String message) {
        super(message);
    }
    private static final String NOT_FOUND = "Quiz not found";
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = NOT_FOUND)
    public HashMap<String, String> handleIndexOutOfBoundsException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", NOT_FOUND);
        response.put("error", e.getClass().getSimpleName());
        return response;
    }
}