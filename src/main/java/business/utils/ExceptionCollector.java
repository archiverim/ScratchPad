package business.utils;

public class ExceptionCollector {
    /**
     * Instance variables
     */

    private boolean hasErrors;
    private final StringBuilder errorMessages;

    /**
     * Constructor
     */

    public ExceptionCollector() {
        hasErrors = false;
        errorMessages = new StringBuilder();
    }

    /**
     * Public methods
     */

    public void addError(String errorMessage) {
        hasErrors = true;
        if (!StringUtility.isNullOrEmptyTrimmed(errorMessage))
            errorMessages.append(errorMessage).append("\n");
    }

    public boolean hasErrors() {
        return hasErrors;
    }

    public String getErrorMessage() {
        return errorMessages.toString();
    }
}
