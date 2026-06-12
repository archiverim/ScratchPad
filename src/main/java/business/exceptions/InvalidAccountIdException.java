package business.exceptions;

import business.constants.Constants.ExceptionMessages;

public class InvalidAccountIdException extends RuntimeException {
    /**
     * Constructor
     */

    public InvalidAccountIdException() {
        super(ExceptionMessages.INVALID_ACCOUNT_ID);
    }
}
