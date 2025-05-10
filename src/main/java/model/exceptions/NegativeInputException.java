package model.exceptions;

public class NegativeInputException extends IllegalArgumentException {

    public NegativeInputException() {
        super();
    }

    public NegativeInputException(String message) {
        super(message);
    }

}
