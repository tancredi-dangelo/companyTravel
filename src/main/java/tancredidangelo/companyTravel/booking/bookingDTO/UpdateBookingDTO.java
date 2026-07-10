package tancredidangelo.companyTravel.booking.bookingDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateBookingDTO(
        @NotNull @Future LocalDate date,
        @NotBlank String notes) {
}
