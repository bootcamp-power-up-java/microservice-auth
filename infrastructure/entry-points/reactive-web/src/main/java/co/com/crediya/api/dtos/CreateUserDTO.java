package co.com.crediya.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Create User request DTO")
public record CreateUserDTO(
        @Schema(example = "John")
        String name,
        @Schema(example = "Doe")
        String lastName,
        @Schema(example = "100200300")
        String documentIdentity,
        @Schema(type = "string", format = "date", example = "1990-05-15")
        LocalDate birthdate,
        @Schema(example = "john.doe@example.com")
        String email,
        @Schema(example = "P@ssw0rd123")
        String password,
        @Schema(example = "+573001234567")
        String phone,
        @Schema(example = "Calle 10 # 20-30, Medellín")
        String address,
        @Schema(example = "150000.50")
        Double baseSalary
) {}
