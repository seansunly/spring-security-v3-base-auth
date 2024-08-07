package co.ruppcstat.ecomercv1.ecomV1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    Map<?, ?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FielErrorss> fieldErrors = new ArrayList<>();
        e.getFieldErrors()
                .forEach(fieldError -> fieldErrors
                        .add(FielErrorss.builder()
                                .field(fieldError.getField())
                                .detail(fieldError.getDefaultMessage())
                                .build()));
        return Map.of("errors", ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .reason(fieldErrors)
                .build());
    }


    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        ErrorResponse<?> errorResponse = ErrorResponse.builder()
                .code(e.getStatusCode().value())
                .reason(e.getReason())
                .build();
        return ResponseEntity
                .status(e.getStatusCode())
                .body(Map.of("error", errorResponse));
    }

}