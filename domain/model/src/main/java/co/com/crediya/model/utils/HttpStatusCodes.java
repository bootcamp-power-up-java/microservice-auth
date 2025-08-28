package co.com.crediya.model.utils;

import lombok.Getter;

@Getter
public enum HttpStatusCodes {
    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    HttpStatusCodes(int code) {
        this.code = code;
    }

}
