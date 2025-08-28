package co.com.crediya.utils;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {

    private String message;
    private String uri;
    private Integer code;
    private String details;
    private LocalDateTime timestamp;

}
