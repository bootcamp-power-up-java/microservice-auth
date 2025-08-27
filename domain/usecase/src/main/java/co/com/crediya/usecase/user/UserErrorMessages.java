package co.com.crediya.usecase.user;

public enum UserErrorMessages {
    NAMES_LAST_NAMES_REQUIRED("First and last names are required."),
    EMAIL_REQUIRED("Email is required."),
    SALARY_REQUIRED("Base salary is required."),
    EMAIL_ALREADY_REGISTERED("This email is already registered."),
    INVALID_EMAIL_FORMAT("The email format is invalid."),
    SALARY_OUT_OF_RANGE("Base salary must be a numeric value between %s and %s.");

    private final String message;

    UserErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getDynamicMessage(Object... args) {
        return String.format(this.message, args);
    }

    public static String getSalaryOutOfRangeMessage() {
        return String.format(SALARY_OUT_OF_RANGE.getMessage(), UserConstraints.MAX_SALARY, UserConstraints.MIN_SALARY);
    }
}
