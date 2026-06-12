package business.exceptions;

public class AccountCredentialsMissingException extends RuntimeException {
    /**
     * Constructor
     */

    public AccountCredentialsMissingException(String message) {
        super(message);
    }
}
