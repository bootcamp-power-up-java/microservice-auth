package co.com.crediya.usecase.user;

import lombok.Getter;

@Getter
public enum UserErrorMessages {
    NAMES_LAST_NAMES_REQUIRED("First and last names are required."),
    EMAIL_REQUIRED("Email is required."),
    SALARY_REQUIRED("Base salary is required."),
    EMAIL_ALREADY_REGISTERED("This email is already registered."),
    INVALID_EMAIL_FORMAT("The email format is invalid."),
    SALARY_OUT_OF_RANGE("Base salary must be a numeric value between %s and %s."),
    USER_NOT_FOUND("User not found."),
    ID_REQUIRED_FOR_UPDATE("User ID is required for update.");

    private final String message;

    UserErrorMessages(String message) {
        this.message = message;
    }

    public static String getNamesLastNamesRequiredMessage() {
        return NAMES_LAST_NAMES_REQUIRED.getMessage();
    }

    public static String getEmailRequiredMessage() {
        return EMAIL_REQUIRED.getMessage();
    }

    public static String getEmailAlreadyRegisteredMessage() {
        return EMAIL_ALREADY_REGISTERED.getMessage();
    }

    public static String getInvalidEmailFormatMessage() {
        return INVALID_EMAIL_FORMAT.getMessage();
    }

    public static String getSalaryOutOfRangeMessage() {
        return String.format(SALARY_OUT_OF_RANGE.getMessage(), UserConstraints.MAX_SALARY, UserConstraints.MIN_SALARY);
    }

    public static String getUserNotFoundMessage() {
        return USER_NOT_FOUND.getMessage();
    }

    public static String getIdRequiredForUpdateMessage() {
        return ID_REQUIRED_FOR_UPDATE.getMessage();
    }

}
