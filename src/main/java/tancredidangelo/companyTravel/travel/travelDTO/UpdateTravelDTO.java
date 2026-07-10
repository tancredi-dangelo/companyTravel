package tancredidangelo.companyTravel.travel.travelDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateTravelDTO(
        @NotBlank String destination,
        @NotNull @Future LocalDate date) {}
