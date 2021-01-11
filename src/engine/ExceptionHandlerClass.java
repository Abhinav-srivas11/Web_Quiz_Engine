package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
public class ExceptionHandlerClass {
    private static final String BAD_REQUEST_MESSAGE = "Bad request for this information";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = BAD_REQUEST_MESSAGE)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", BAD_REQUEST_MESSAGE);
        response.put("error", ex.getClass().getSimpleName());
        return response;
    }
}
