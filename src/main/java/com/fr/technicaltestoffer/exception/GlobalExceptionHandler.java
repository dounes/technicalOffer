package com.fr.technicaltestoffer.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value(value = "${exception.message.userNotFound}")
    private String userNotFound;
    @Value(value = "${exception.message.userAlreadyExist}")
    private String userAlreadyExist;
    @Value(value = "${exception.message.badRequest}")
    private String badRequest;
    @Value(value = "${exception.message.userNotFrenchOrUnderAgeException}")
    private String userNotFrenchOrUnderAgeException;

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<String>(userNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> badRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<Object> userAlreadyExistException(BadRequestException serverErrorException) {
        return new ResponseEntity<>(userAlreadyExist, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserNotFrenchOrUnderAgeException.class)
    public ResponseEntity<Object> userNotFrenchOrUnderAgeException(UserNotFrenchOrUnderAgeException serverErrorException) {
        return new ResponseEntity<>(userNotFrenchOrUnderAgeException, HttpStatus.BAD_REQUEST);
    }
}
