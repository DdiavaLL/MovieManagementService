package com.vlter.mmservice.restservice.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Tereshchenko on 02.11.2020.
 */

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(ThereIsNoSuchMovieException.class)
    protected ResponseEntity<ControllerException> handleThereIsNoSuchMovieException() {
        return new ResponseEntity<ControllerException>(new ControllerException("There is no such movie"), HttpStatus.NOT_FOUND);
    }

    private static class ControllerException {
        private String message;

        public ControllerException() {
        }

        public ControllerException(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
