package tancredidangelo.companyTravel.entities.travel.travelDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewTravelDTO(
        @NotBlank String destination,
        @NotNull @Future LocalDate date) {}
