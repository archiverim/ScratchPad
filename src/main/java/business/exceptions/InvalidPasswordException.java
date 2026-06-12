package business.exceptions;

import business.constants.Constants.ExceptionMessages;

public class InvalidPasswordException extends RuntimeException {
    /**
     * Constructor
     */

    public InvalidPasswordException() {
        super(ExceptionMessages.INVALID_PASSWORD);
    }
}
