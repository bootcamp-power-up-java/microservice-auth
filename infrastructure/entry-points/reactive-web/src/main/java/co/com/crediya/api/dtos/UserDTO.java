package co.com.crediya.api.dtos;

public record UserDTO(
        String id,
        String name,
        String lastName,
        String documentIdentity,
        String birthdate,
        String email,
        String password,
        String phone,
        String address,
        Double baseSalary,
        String roleId
) {
}
