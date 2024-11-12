package com.dna.sequence.DnaSequence.exception;

import com.dna.sequence.DnaSequence.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionResponseDto> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                String.valueOf(status.value()),
                status.name(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(status).body(exceptionResponse);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<ExceptionResponseDto> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                String.valueOf(status.value()),
                status.name(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(status).body(exceptionResponse);
    }
}