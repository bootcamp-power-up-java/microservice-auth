package co.com.crediya.api.errorhandling;

import co.com.crediya.utils.ExceptionResponse;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ExceptionResponse> handleDecodingError(Exception ex, ServerWebExchange exchange) {
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .uri(exchange.getRequest().getPath().value())
                .code(HttpStatus.BAD_REQUEST.value())
                .details("An unexpected error occurred")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
