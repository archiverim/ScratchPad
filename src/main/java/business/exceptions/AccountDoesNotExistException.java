package business.exceptions;

import business.constants.Constants.ExceptionMessages;

public class AccountDoesNotExistException extends RuntimeException {
    /**
     * Constructor
     */

    public AccountDoesNotExistException() {
        super(ExceptionMessages.ACCOUNT_DOES_NOT_EXIST);
    }
}
