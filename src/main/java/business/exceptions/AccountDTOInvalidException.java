package business.exceptions;

import business.constants.Constants;

public class AccountDTOInvalidException extends RuntimeException {
    /**
     * Constructor
     */

    public AccountDTOInvalidException() {
        super(Constants.ExceptionMessages.INVALID_ACCOUNT_DTO);
    }
}
