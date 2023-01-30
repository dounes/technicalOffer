package com.fr.testairfrance.usermanagement.exception;

public class UserNotFrenchOrUnderAgeException extends RuntimeException{
    private String message;

    public UserNotFrenchOrUnderAgeException(String message) {
        super(message);
        this.message = message;
    }

    public UserNotFrenchOrUnderAgeException() {
    }
}
