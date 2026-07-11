package tancredidangelo.companyTravel.entities.booking.bookingDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewBookingDTO(
        @NotBlank Long employeeId,
        @NotBlank Long travelId,
        @NotNull @Future LocalDate date,
        @NotBlank String notes
) {}
