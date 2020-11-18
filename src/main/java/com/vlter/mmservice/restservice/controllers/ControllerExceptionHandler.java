package com.vlter.mmservice.restservice.controllers;

import com.vlter.mmservice.restservice.exceptions.ControllerException;
import com.vlter.mmservice.restservice.exceptions.DeleteException;
import com.vlter.mmservice.restservice.exceptions.IncorrectSaveException;
import com.vlter.mmservice.restservice.exceptions.ThereIsNoSuchMovieException;
import com.vlter.mmservice.restservice.exceptions.ValidationException;
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

    @ExceptionHandler(IncorrectSaveException.class)
    protected ResponseEntity<ControllerException> handleIncorrectAddingException() {
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ошибка во время добавления нового кинофильма!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DeleteException.class)
    protected ResponseEntity<ControllerException> handleDeleteException() {
        return new ResponseEntity<ControllerException>(new ControllerException(HttpStatus.BAD_REQUEST.value(), "Ошибка во время удаления кинофильма!"), HttpStatus.BAD_REQUEST);
    }
}
