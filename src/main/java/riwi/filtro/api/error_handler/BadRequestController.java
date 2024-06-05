package riwi.filtro.api.error_handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import riwi.filtro.api.dtos.errors.ErrorsResp;
import riwi.filtro.utils.exceptions.BadRequestException;
import riwi.filtro.api.dtos.errors.BaseErrorResp;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST )
public class BadRequestController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResp handleBadRequest(MethodArgumentNotValidException exception){
        List<Map<String, String>> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(e -> {
            Map<String, String> error = new HashMap<>();
            error.put("Error",e.getDefaultMessage());
            error.put("Error",e.getField());
            errors.add(error);
        });

        return ErrorsResp.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    public BaseErrorResp handleError(BadRequestException exception){
        List<Map<String,String>> errors = new ArrayList<>();

        Map<String,String> error = new HashMap<>();
        
        error.put("id", exception.getMessage());

        return ErrorsResp.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(errors)
                .build();
    }
}