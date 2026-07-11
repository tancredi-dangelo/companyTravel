package tancredidangelo.companyTravel.entities.employee.employeeDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record NewEmployeeDTO(
        @NotBlank @Max(30) String name,
        @NotBlank @Max(30) String surname,
        @NotBlank @Email @Min(6) @Max(30) String email) {
}
