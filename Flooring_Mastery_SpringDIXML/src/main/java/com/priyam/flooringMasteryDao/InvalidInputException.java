package com.priyam.flooringMasteryDao;

public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {

        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {

        super(message, cause);
    }
}
