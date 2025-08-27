package co.com.crediya.r2dbc.constants;

public final class UserQueryConstants {
    private UserQueryConstants() {}

    public static final String FIND_BY_EMAIL = "SELECT * FROM " + UserEntityConstants.TABLE_NAME + " u WHERE LOWER(u.email) = LOWER(:email)";

}
