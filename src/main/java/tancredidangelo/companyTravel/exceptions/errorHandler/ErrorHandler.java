package tancredidangelo.companyTravel.exceptions.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tancredidangelo.companyTravel.exceptions.BadRequestException;
import tancredidangelo.companyTravel.exceptions.InternalServerErrorException;
import tancredidangelo.companyTravel.exceptions.NotFoundException;

import java.time.LocalDateTime;



@RestControllerAdvice
public class ErrorHandler {

    /// BAD_REQUEST (400)
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO badRequestHandler(BadRequestException ex) {
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }


    /// NOT_FOUND (404)
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO notFoundHandler(NotFoundException ex) {
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }



    /// INTERNAL_SERVER_ERROR (500)
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO internalServerErrorHandler(InternalServerErrorException ex) {
        ex.printStackTrace();
        return new ErrorDTO("Unknown server-side error occurred.", LocalDateTime.now());
    }


}

