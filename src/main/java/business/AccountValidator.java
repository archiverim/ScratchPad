package business;

import business.constants.Constants;
import business.exceptions.AccountCredentialsMissingException;
import business.exceptions.AccountTitleMissingException;
import business.exceptions.AccountValidationException;
import business.exceptions.InvalidPasswordException;
import business.utils.ExceptionCollector;
import business.utils.StringUtility;

public class AccountValidator {
    /**
     * Instance variables
     */

    private static AccountValidator instance;

    /**
     * Constructor
     */

    // Implement the Singleton pattern
    private AccountValidator() {}

    /**
     * Public methods
     */

    // Return the instance of the validator with lazy initialization
    // Not worried about thread safety in this App as it is only used in
    // once service.
    public static AccountValidator getInstance() {
        if (instance == null)
            instance = new AccountValidator();
        return instance;
    }

    // Collects all exception messages and throws a combined exception if any were
    // caught
    public void validateNewAccountFields(String title, String username, String password) {
        ExceptionCollector collector = new ExceptionCollector();

        try {
            validateAccountTitle(title);
        } catch (AccountTitleMissingException e) {
            collector.addError(e.getMessage());
        }

        try {
            validateAccountUsername(username);
        } catch (AccountCredentialsMissingException e) {
            collector.addError(e.getMessage());
        }

        try {
            validateAccountPassword(password);
        } catch (AccountCredentialsMissingException | InvalidPasswordException e) {
            collector.addError(e.getMessage());
        }

        if (collector.hasErrors())
            throw new AccountValidationException(collector.getErrorMessage());
    }

    // Must be non-empty and non-whitespace
    public void validateAccountTitle(String title) {
        if (StringUtility.isNullOrEmptyTrimmed(title))
            throw new AccountTitleMissingException();
    }

    // Must be non-empty and non-whitespace
    public void validateAccountUsername(String username) {
        if (StringUtility.isNullOrEmptyTrimmed(username))
            throw new AccountCredentialsMissingException(Constants.ExceptionMessages.ACCOUNT_USERNAME_MISSING);
    }

    // Must be non-empty, length in range, and match regex. Can include whitespace
    // (will not trim a password)
    public void validateAccountPassword(String password) {
        if (StringUtility.isNullOrEmptyTrimmed(password))
            throw new AccountCredentialsMissingException(Constants.ExceptionMessages.ACCOUNT_PASSWORD_MISSING);

        if (StringUtility.hasLeadingOrTrailingWhitespace(password)) {
            throw new InvalidPasswordException();
        }

        if (password.length() > Constants.MAX_PASSWORD_LEN)
            throw new InvalidPasswordException();

        if (!password.matches(Constants.PASSWORD_REGEX))
            throw new InvalidPasswordException();
    }
}
