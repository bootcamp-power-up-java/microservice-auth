package co.com.crediya.model.utils;

import lombok.Getter;

@Getter
public enum GeneralErrorMessages {
    FIELDS_REQUIRED("Some required fields are missing."),
    INVALID_DATA("The data provided is invalid."),
    RESOURCE_ALREADY_EXISTS("The resource already exists."),
    RESOURCE_NOT_FOUND("The requested resource was not found."),
    OPERATION_NOT_ALLOWED("The requested operation is not allowed.");

    private final String message;

    GeneralErrorMessages(String message) {
        this.message = message;
    }
}
