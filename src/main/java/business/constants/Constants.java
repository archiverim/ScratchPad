package business.constants;

public class Constants {
    /**
     * Constants
     */

    public static final class ExceptionMessages {
        public static final String ACCOUNT_USERNAME_MISSING = "Account username cannot be empty or all whitespace.";
        public static final String ACCOUNT_PASSWORD_MISSING = "Account password cannot be empty.";
        public static final String ACCOUNT_TITLE_MISSING = "Account title cannot be empty or all whitespace.";
        public static final String INVALID_PASSWORD =
                String.format("Password length must be between %d and %d and only "
                                + "consist of visible ASCII characters. "
                                + "(Cannot start or end with spaces)",
                        MIN_PASSWORD_LEN, MAX_PASSWORD_LEN);
        public static final String INVALID_ACCOUNT_ID =
                String.format("Account ID value must be greater than %d.", MIN_ACCOUNT_ID);
        public static final String ACCOUNT_DOES_NOT_EXIST =
                "An operation was attempted on an account that does not exist";
        public static final String INVALID_ACCOUNT_DTO =
                "Failed to create a new account, the provided data was invalid.";
        public static final String EMPTY_PASSWORD = "Password cannot be empty.";
        public static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match.";
        public static final String INVALID_LOGIN_CREDENTIALS = "Incorrect password. Please try again.";
        public static final String DATABASE_INITIALIZATION_ERROR =
                "An error occurred while initializing the database. Please try again.";
    }

    public static final class PasswordGenerator {
        public static final String UPPERCASE_LATIN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public static final String LOWERCASE_LATIN_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        public static final String NUMBERS = "0123456789";
        public static final String SPECIAL_SYMBOLS = "!@#$%^&*()-_=+[]{};:,.<>?";
        public static final String ALLOWED_CHARACTERS =
                UPPERCASE_LATIN_ALPHABET + LOWERCASE_LATIN_ALPHABET + NUMBERS + SPECIAL_SYMBOLS;
    }

    public static final String EMPTY_STRING = "";
    public static final int MAX_ACCOUNTS_TO_DISPLAY = 10000;
    public static final int MAX_PASSWORD_LEN = 100;
    public static final int MIN_PASSWORD_LEN = 1;
    public static final String PASSWORD_REGEX = "\\A\\p{ASCII}+\\z";
    public static final int MIN_ACCOUNT_ID = 0;
}
