package co.com.crediya.api.dtos;

public record UpdateUserDTO(
        String name,
        String lastName,
        String password,
        String phone,
        String address,
        Double baseSalary,
        String roleId
) {
}
