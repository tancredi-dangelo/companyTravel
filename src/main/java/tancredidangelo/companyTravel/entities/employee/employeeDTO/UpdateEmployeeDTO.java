package tancredidangelo.companyTravel.entities.employee.employeeDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateEmployeeDTO(
        @NotBlank @Max(30) String name,
        @NotBlank @Max(30) String surname,
        @NotBlank @Min(8) @Max(20) String username) { }
