package com.fr.technicaltestoffer.exception;

public class UserNotFrenchOrUnderAgeException extends RuntimeException{
    private String message;

    public UserNotFrenchOrUnderAgeException(String message) {
        super(message);
        this.message = message;
    }

    public UserNotFrenchOrUnderAgeException() {
    }
}
