package persistence.exceptions;

public class AccountNotFoundException extends RuntimeException {
    // Custom error message
    public AccountNotFoundException(String message) {
        super(message);
    }
}
