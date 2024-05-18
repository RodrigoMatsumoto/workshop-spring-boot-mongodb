package com.example.workshopmongodb.resources.exception;

import com.example.workshopmongodb.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(
      ObjectNotFoundException objectNotFoundException, HttpServletRequest httpServletRequest
  ) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    StandardError standardError = new StandardError(
        System.currentTimeMillis(),
        httpStatus.value(),
        "Not found",
        objectNotFoundException.getMessage(),
        httpServletRequest.getRequestURI()
    );

    return ResponseEntity.status(httpStatus).body(standardError);
  }
}