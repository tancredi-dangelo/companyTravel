package tancredidangelo.companyTravel.employee.employeeDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateEmployeeEmailDTO(@NotBlank @Email String email) {}
