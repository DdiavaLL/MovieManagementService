package com.vlter.mmservice.restservice.controllers;

import com.vlter.mmservice.restservice.exceptions.*;
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
    @ExceptionHandler(ValidationException.class)
    protected  ResponseEntity<ControllerException> handleValidationException(ValidationException ex) {
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.BAD_REQUEST.value(), "Ошибка валидации! " + ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ThereIsNoSuchMovieException.class)
    protected ResponseEntity<ControllerException> handleThereIsNoSuchMovieException() {
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.NOT_FOUND.value(), "Данного фильма не существует!"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectMovieSaveException.class)
    protected ResponseEntity<ControllerException> handleIncorrectMovieAddingException() {
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ошибка во время добавления нового кинофильма!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DeleteMovieException.class)
    protected ResponseEntity<ControllerException> handleDeleteMovieException() {
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.BAD_REQUEST.value(), "Ошибка во время удаления кинофильма!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteDirectorException.class)
    protected ResponseEntity<ControllerException> handleDeleteDirectorException() {
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.BAD_REQUEST.value(), "Ошибка во время удаления режиссера!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectDirectorSaveException.class)
    protected ResponseEntity<ControllerException> handleIncorrectDirectorAddingException() {
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ошибка во время добавления нового режиссера!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ThereIsNoSuchDirectorException.class)
    protected ResponseEntity<ControllerException> handleThereIsNoSuchDirectorException() {
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.NOT_FOUND.value(), "Данного режиссера не существует!"), HttpStatus.NOT_FOUND);
    }
}
