package pm.project.restaurante_app.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pm.project.restaurante_app.utils.GenericResponse;
import pm.project.restaurante_app.utils.Global;

@RestControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(Exception.class)
    public GenericResponse genericException(Exception ex) {
        return new GenericResponse("exception", -1, Global.OPERACION_ERRONEA, ex.getMessage());
    }
}