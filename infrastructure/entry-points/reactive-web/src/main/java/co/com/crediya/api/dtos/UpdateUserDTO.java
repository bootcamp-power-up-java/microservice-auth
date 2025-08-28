package co.com.crediya.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateUserDTO(
        @Schema(example = "John")
        String name,

        @Schema(example = "Doe")
        String lastName,

        @Schema(example = "N3wP@ssw0rd")
        String password,

        @Schema(example = "+573201112233")
        String phone,

        @Schema(example = "Cra 50 #20-10, Bogotá")
        String address,

        @Schema(example = "1800000.75")
        Double baseSalary,

        @Schema(example = "7674e918-1a93-4381-bd48-8018dbeb8087")
        String roleId
) {}

