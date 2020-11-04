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
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.NOT_FOUND.value(), "There is no such movie"), HttpStatus.NOT_FOUND);
    }

    private static class ControllerException {
        private Integer status;
        private String reason;

        public ControllerException() {
        }

        public ControllerException(Integer status, String reason) {
            this.status = status;
            this.reason = reason;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
