package com.vault.examen.controller.advice;

import com.vault.examen.vo.ApiError;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        LOGGER.error("[BAD_REQUEST] Error de peticion. Exception: " + exception.getMessage());
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

}
