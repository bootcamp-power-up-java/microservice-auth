package co.com.crediya.usecase.user;

public final class UserConstrains {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public static final double SALARIO_MINIMO = 0.0;
    public static final double SALARIO_MAXIMO = 15000000.0;

    private UserConstrains() { }
}
