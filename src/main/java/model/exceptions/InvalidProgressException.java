package model.exceptions;

public class InvalidProgressException extends IllegalArgumentException {

    public InvalidProgressException() {
        super();
    }

    public InvalidProgressException(String message) {
        super(message);
    }

}