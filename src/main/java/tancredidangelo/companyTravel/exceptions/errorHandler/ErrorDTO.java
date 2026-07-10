package tancredidangelo.companyTravel.exceptions.errorHandler;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime timeStamp) {
}
