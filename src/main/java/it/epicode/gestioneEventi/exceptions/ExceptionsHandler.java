package it.epicode.gestioneEventi.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = EntityExistsException.class)
    protected ResponseEntity<Error> alreadyExistsException(EntityExistsException ex) {
        Error error = new Error();
        error.setMessage("Entity already exists");
        error.setDetails(ex.getMessage());
        error.setStatus("409");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value= EntityNotFoundException.class)
    protected ResponseEntity<Error> entityNotFound(EntityNotFoundException ex) {
        Error error = new Error();
        error.setMessage("Entity not found");
        error.setDetails(ex.getMessage());
        error.setStatus("404");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= PostiEsauritiException.class)
protected ResponseEntity<Error> postiEsauritiExceltion (PostiEsauritiException ex) {
        Error error = new Error();
        error.setMessage("Posti esauriti exception");
        error.setDetails(ex.getMessage());
        error.setStatus("409");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
