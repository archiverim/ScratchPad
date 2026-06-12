package persistence.constants;

public class Constants {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final class ExceptionMessages {
        public static final String ACCOUNT_NOT_FOUND = "Account with ID %d could not be found.";
        public static final String ACCOUNT_NOT_FOUND_FOR_DELETION = "Account with ID %d could not be found for deletion.";
        public static final String ACCOUNT_NOT_FOUND_FOR_UPDATE = "Account with ID %d could not be found for update.";
        public static final String ACCOUNT_SAVE_FAILURE = "Failed to save the account.";
        public static final String DATABASE_INITIALIZATION_FAILED = "Failed to initialize the database.";
    }

    public static final class AccountFieldNames {
        public static final String ACCOUNT_TITLE = "title";
        public static final String ACCOUNT_USERNAME = "username";
        public static final String ACCOUNT_PASSWORD_DB = "password";
        public static final String ACCOUNT_URL = "url";
        public static final String ACCOUNT_NOTE = "note";
        public static final String ACCOUNT_PINNED = "pinned";
        public static final String ACCOUNT_ID = "id";
        public static final String ACCOUNT_CREATED_AT = "createdAt";
        public static final String ACCOUNT_LAST_INTERACT = "lastInteract";
        public static final String ACCOUNT_INTERACTION_COUNT = "interactionCount";
    }
}
