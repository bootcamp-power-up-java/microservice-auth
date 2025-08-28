package co.com.crediya.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User response DTO")
public record UserDTO(
        @Schema(example = "f2435d60-820a-4d5e-b086-69ed11053341") String id,
        @Schema(example = "John") String name,
        @Schema(example = "Doe") String lastName,
        @Schema(example = "100200300") String documentIdentity,
        @Schema(example = "1990-05-15") String birthdate,
        @Schema(example = "john.doe@example.com") String email,
        @Schema(example = "****") String password,
        @Schema(example = "+57 3001234567") String phone,
        @Schema(example = "Cra 10 #20-30") String address,
        @Schema(example = "2500.00") Double baseSalary,
        @Schema(example = "f2435d60-820a-4d5e-b086-69ed11053341") String roleId
) {
}
