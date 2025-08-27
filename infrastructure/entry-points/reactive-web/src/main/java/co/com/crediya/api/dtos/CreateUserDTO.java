package co.com.crediya.api.dtos;

public record CreateUserDTO(
        String name,
        String lastName,
        String documentIdentity,
        String birthdate,
        String email,
        String password,
        String phone,
        String address,
        double baseSalary
) {
}
