package business.exceptions;

import business.constants.Constants.ExceptionMessages;

public class AccountTitleMissingException extends RuntimeException {
    /**
     * Constructor
     */

    public AccountTitleMissingException() {
        super(ExceptionMessages.ACCOUNT_TITLE_MISSING);
    }
}
