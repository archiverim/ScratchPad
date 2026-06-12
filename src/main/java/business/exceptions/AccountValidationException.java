package business.exceptions;

public class AccountValidationException extends RuntimeException {
    /**
     * Constructor
     */

    public AccountValidationException(String message) {
        super(message);
    }
}
