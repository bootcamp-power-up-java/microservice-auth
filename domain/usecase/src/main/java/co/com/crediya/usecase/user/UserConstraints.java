package co.com.crediya.usecase.user;

public final class UserConstraints {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public static final double MIN_SALARY = 0.0;
    public static final double MAX_SALARY = 15000000.0;

    private UserConstraints() { }
}
