package nature.calculadoraco2.exception;

import nature.calculadoraco2.Dto.ApiExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ApiExceptionDto> handleValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getAllErrors().stream().map((exValidation) -> new ApiExceptionDto("Erro de validação", exValidation.getDefaultMessage(), Instant.now())).toList();
    }
}
