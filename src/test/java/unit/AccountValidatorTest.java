package unit;

import business.AccountValidator;
import business.exceptions.AccountCredentialsMissingException;
import business.exceptions.AccountTitleMissingException;
import business.exceptions.AccountValidationException;
import business.exceptions.InvalidPasswordException;
import business.constants.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AccountValidatorTest {
    private AccountValidator validator;

    @BeforeEach
    public void init() {
        validator = AccountValidator.getInstance();
    }

    @Nested
    class AddingAccountValidation {
        @Test // An added account with empty title fails
        public void newAccountEmptyTitle() {
            String title = "";
            String username = "username";
            String password = "a".repeat(Constants.MIN_PASSWORD_LEN);
            assertThrows(AccountValidationException.class,
                    () -> validator.validateNewAccountFields(title, username, password));
        }

        @Test // An added account with empty password but not username does not fail
        public void newAccountEmptyPassword() {
            String title = "title";
            String username = "username";
            String password = "";
            assertThrows(AccountValidationException.class,
                    () -> validator.validateNewAccountFields(title, username, password));
        }

        @Test // An added account with empty username but not password does not fail
        public void newAccountEmptyUsername() {
            String title = "title";
            String username = "";
            String password = "a".repeat(Constants.MIN_PASSWORD_LEN);
            assertThrows(AccountValidationException.class,
                    () -> validator.validateNewAccountFields(title, username, password));
        }

        @Test // An added account with empty username and password fails
        public void newAccountEmptyUsernamePassword() {
            String title = "title";
            String username = "";
            String password = "";
            assertThrows(AccountValidationException.class,
                    () -> validator.validateNewAccountFields(title, username, password));
        }
    }

    @Nested
    class AccountTitleValidation {
        @Test // A null title
        public void nullTitle() {
            // Arrange
            String title = null;
            // Act and assert
            assertThrows(AccountTitleMissingException.class, () -> validator.validateAccountTitle(title));
        }

        @Test
        public void emptyTitle() {
            // Arrange
            String title = "";
            // Act and assert
            assertThrows(AccountTitleMissingException.class, () -> validator.validateAccountTitle(title));
        }

        @Test // Whitespace title
        public void whitespaceTitle() {
            // Arrange
            String title = "        ";
            // Act and assert
            assertThrows(AccountTitleMissingException.class, () -> validator.validateAccountTitle(title));
        }

        @Test
        public void validTitle() {
            // Arrange
            String title = "Any string will do.";
            // Act and assert
            assertDoesNotThrow(() -> validator.validateAccountTitle(title));
        }
    }

    @Nested
    class AccountPasswordValidation {
        @Test // An empty password throws an exception
        public void newPasswordValidatorEmpty() {
            String password = "";
            assertThrows(AccountCredentialsMissingException.class, () -> validator.validateAccountPassword(password));
        }

        @Test // Password with extended chars throws an exception
        public void newPasswordValidatorExtendedChars() {
            String password = "a".repeat(Constants.MIN_PASSWORD_LEN) + "\u00E9"
                    + "\u00F2";
            // Assert this is a valid password to test
            assertTrue(
                    password.length() >= Constants.MIN_PASSWORD_LEN && password.length() <= Constants.MAX_PASSWORD_LEN);
            assertThrows(InvalidPasswordException.class, () -> validator.validateAccountPassword(password));
        }

        @Test // Max length password does not throw
        public void newPasswordValidatorMaxLength() {
            String password = "a".repeat(Constants.MAX_PASSWORD_LEN);
            assertDoesNotThrow(() -> validator.validateAccountPassword(password));
        }

        @Test // Min length password does not throw
        public void newPasswordValidatorMinLength() {
            String password = "a".repeat(Constants.MIN_PASSWORD_LEN);
            assertDoesNotThrow(() -> validator.validateAccountPassword(password));
        }

        @Test // All spaces throws an exception
        public void newPasswordValidatorSpaces() {
            String password = " ".repeat(Constants.MIN_PASSWORD_LEN);
            assertThrows(AccountCredentialsMissingException.class, () -> validator.validateAccountPassword(password));
        }

        @Test // Trailing whitespace throws an exception
        public void newPasswordValidatorTrailingSpaces() {
            String password = "password ";
            assertThrows(InvalidPasswordException.class, () -> validator.validateAccountPassword(password));
        }

        @Test // Trailing whitespace throws an exception
        public void newPasswordValidatorLeadingSpaces() {
            String password = " password";
            assertThrows(InvalidPasswordException.class, () -> validator.validateAccountPassword(password));
        }

        @Test // Too long password throws an exception
        public void newPasswordValidatorTooLong() {
            String password = "a".repeat(Constants.MAX_PASSWORD_LEN + 1);
            assertThrows(InvalidPasswordException.class, () -> validator.validateAccountPassword(password));
        }

        @Test // ASCII string does not throw
        public void newPasswordValidatorValid() {
            String password = "1234abCD!@#$ $%^";
            // Check to make sure we have formed a valid password before the actual test
            assertTrue(
                    password.length() >= Constants.MIN_PASSWORD_LEN && password.length() <= Constants.MAX_PASSWORD_LEN);
            assertDoesNotThrow(() -> validator.validateAccountPassword(password));
        }
    }
}
