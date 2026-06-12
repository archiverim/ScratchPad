package persistence.exceptions;

import persistence.constants.Constants.ExceptionMessages;

public class DatabaseInitializationException extends RuntimeException {
    // Default error message
    public DatabaseInitializationException() {
        super(ExceptionMessages.DATABASE_INITIALIZATION_FAILED);
    }
}
