package exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APINotFoundException.class)
    public CustomException apiException() {
        CustomException error = new CustomException();
        error.setHttp_status(404);
        error.setError_msg("API not found.");
        return error;
    }

    @ExceptionHandler(DBNotFoundException.class)
    public CustomException dbException() {
        CustomException error = new CustomException();
        error.setHttp_status(500);
        error.setError_msg("DB not found.");
        return error;
    }

}
