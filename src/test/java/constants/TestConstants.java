package constants;

import models.dtos.AccountDTO;
import models.Account;

import java.time.LocalDateTime;

public final class TestConstants {
    private TestConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // For test database setup
    public static final String RELATIONAL_DB_TEST_FILEPATH = "test.db";
    public static final String RELATIONAL_DB_TEST_PASSWORD = "test-password";

    // A fixed baseline time to ensure deterministic test results
    private static final LocalDateTime BASE_TIME = LocalDateTime.of(2026, 1, 1, 12, 0);

    // Accounts that we know the lastInteract value of
    public static final class AccessTimeSortingTests {
        public static final Account OLD_ACCESS = new Account.Builder()
                                                         .id(1)
                                                         .title("Old")
                                                         .username("old")
                                                         .password("pwd")
                                                         .url("url")
                                                         .note("note")
                                                         .createdAt(BASE_TIME.minusDays(5))
                                                         .lastInteract(BASE_TIME.minusDays(5))
                                                         .interactionCount(5)
                                                         .isPinned(false)
                                                         .build();
        public static final Account RECENT_ACCESS = new Account.Builder()
                                                            .id(2)
                                                            .title("Recent")
                                                            .username("recent")
                                                            .password("pwd")
                                                            .url("url")
                                                            .note("note")
                                                            .createdAt(BASE_TIME)
                                                            .lastInteract(BASE_TIME)
                                                            .interactionCount(2)
                                                            .isPinned(false)
                                                            .build();
    }

    // Accounts that we know the isPinned value of for sorting
    public static final class AccountPinningSortingTests {
        public static final Account FAV_ACC_1 = new Account.Builder()
                                                        .id(1)
                                                        .title("Favourite 1")
                                                        .username("u1")
                                                        .password("pwd")
                                                        .url("url")
                                                        .note("note")
                                                        .createdAt(BASE_TIME)
                                                        .lastInteract(BASE_TIME)
                                                        .interactionCount(0)
                                                        .isPinned(true)
                                                        .build();

        public static final Account FAV_ACC_2 = new Account.Builder()
                                                        .id(2)
                                                        .title("Favourite 2")
                                                        .username("u2")
                                                        .password("pwd")
                                                        .url("url")
                                                        .note("note")
                                                        .createdAt(BASE_TIME)
                                                        .lastInteract(BASE_TIME)
                                                        .interactionCount(0)
                                                        .isPinned(true)
                                                        .build();

        public static final Account NOT_FAV_ACC_1 = new Account.Builder()
                                                            .id(3)
                                                            .title("Not Favourite")
                                                            .username("u3")
                                                            .password("pwd")
                                                            .url("url")
                                                            .note("note")
                                                            .createdAt(BASE_TIME)
                                                            .lastInteract(BASE_TIME)
                                                            .interactionCount(0)
                                                            .isPinned(false)
                                                            .build();

        public static final Account NOT_FAV_ACC_2 = new Account.Builder()
                                                            .id(4)
                                                            .title("Not Favourite 2")
                                                            .username("u3")
                                                            .password("pwd")
                                                            .url("url")
                                                            .note("note")
                                                            .createdAt(BASE_TIME)
                                                            .lastInteract(BASE_TIME)
                                                            .interactionCount(0)
                                                            .isPinned(false)
                                                            .build();
    }

    // Some assertion messages in case of assertion failures
    public static final class AssertionMessages {
        public static final String CHRONOLOGICALLY = "Sort the most recently accessed record to the top.";
        public static final String DB_DELETION_FAILED = "Failed to delete the test database in integration tests.";
        public static final String DESCENDING_ORDER = "Clear the highest interaction record first.";
        public static final String DATABASE_EMPTY_E2E_DELETE =
                "Database must have at least one account to run this test.";
    }

    public static final class CatalogServiceTests {
        public static final String TITLE_SEARCH_TEXT = "Alpha";

        public static final Account ACCOUNT_ALPHA_POPULAR = new Account.Builder()
                                                                    .id(2)
                                                                    .title("Alpha Analytics")
                                                                    .username("alpha_admin")
                                                                    .password("encrypted2")
                                                                    .url("https://alpha.com")
                                                                    .note("Primary corporate account.")
                                                                    .createdAt(BASE_TIME.minusDays(1))
                                                                    .lastInteract(BASE_TIME.plusHours(2))
                                                                    .interactionCount(50)
                                                                    .isPinned(false)
                                                                    .build();

        public static final Account ACCOUNT_ALPHA_NEWEST = new Account.Builder()
                                                                   .id(3)
                                                                   .title("ALPHA BETA CO")
                                                                   .username("betatester")
                                                                   .password("encrypted3")
                                                                   .url("https://beta.alpha.org")
                                                                   .note("Testing environment.")
                                                                   .createdAt(BASE_TIME)
                                                                   .lastInteract(BASE_TIME.plusHours(4))
                                                                   .interactionCount(5)
                                                                   .isPinned(false)
                                                                   .build();

        public static final Account ACCOUNT_PINNED_PARTIAL = new Account.Builder()
                                                                     .id(4)
                                                                     .title("Team Alpha Project")
                                                                     .username("alpha_team")
                                                                     .password("encrypted4")
                                                                     .url("https://teams.com")
                                                                     .note("Shared credentials.")
                                                                     .createdAt(BASE_TIME.minusDays(2))
                                                                     .lastInteract(BASE_TIME.minusHours(1))
                                                                     .interactionCount(12)
                                                                     .isPinned(true)
                                                                     .build();

        public static final Account ACCOUNT_ZETA_CONTROL = new Account.Builder()
                                                                   .id(5)
                                                                   .title("Zeta Supply")
                                                                   .username("zetasupply")
                                                                   .password("encrypted5")
                                                                   .url("https://zeta.io")
                                                                   .note("Unrelated data.")
                                                                   .createdAt(BASE_TIME.minusDays(2))
                                                                   .lastInteract(BASE_TIME.minusHours(5))
                                                                   .interactionCount(5)
                                                                   .isPinned(false)
                                                                   .build();
    }

    // Accounts that we know the order of creation time
    public static final class CreationTimeSortingTests {
        public static final Account ACCOUNT_OLDEST = new Account.Builder()
                                                             .id(1)
                                                             .title("Oldest Account")
                                                             .username("oldestaccount")
                                                             .password("encrypted1")
                                                             .url("url1")
                                                             .note("note1")
                                                             .createdAt(BASE_TIME.minusDays(3))
                                                             .lastInteract(BASE_TIME)
                                                             .interactionCount(0)
                                                             .isPinned(false)
                                                             .build();

        public static final Account ACCOUNT_MIDDLE = new Account.Builder()
                                                             .id(2)
                                                             .title("Newer Account")
                                                             .username("neweraccount")
                                                             .password("encrypted2")
                                                             .url("url2")
                                                             .note("note2")
                                                             .createdAt(BASE_TIME.minusDays(2))
                                                             .lastInteract(BASE_TIME)
                                                             .interactionCount(0)
                                                             .isPinned(false)
                                                             .build();

        public static final Account ACCOUNT_NEWEST = new Account.Builder()
                                                             .id(3)
                                                             .title("Newest Account")
                                                             .username("newestaccount")
                                                             .password("encrypted3")
                                                             .url("url3")
                                                             .note("note3")
                                                             .createdAt(BASE_TIME.minusDays(1))
                                                             .lastInteract(BASE_TIME)
                                                             .interactionCount(0)
                                                             .isPinned(false)
                                                             .build();
    }

    // Account that we know the ID of to delete
    public static final class DeleteUpdateTests {
        public static final Account TO_DELETE = new Account.Builder()
                                                        .id(1)
                                                        .title("Title")
                                                        .username("Username")
                                                        .password("password")
                                                        .url("url")
                                                        .note("note")
                                                        .createdAt(BASE_TIME)
                                                        .lastInteract(BASE_TIME)
                                                        .interactionCount(0)
                                                        .isPinned(false)
                                                        .build();
    }

    // Constants for end-to-end tests
    public final static class EndToEndTests {
        public static final String ACC_CARD_CLASS = ".account-card";
        public static final String ACC_CARD_DELETE_BUTTON_ID = "#account-card-delete-btn";
        public static final String ACC_DELETE_CANCEL_DIALOG = "Cancel";
        public static final String ADD_ACC_BUTTON_ID = "#add-account-btn";
        public static final String CREATE_ACC_BUTTON_ID = "#create-account-btn";
        public static final String NOTES_INPUT_ID = "#acc-edit-notes-input";
        public static final String PASSWORD_INPUT_ID = "#acc-edit-password-input";
        public static final String TEST_ACC_NAME = "E2E Test Account";
        public static final String TEST_ACC_NOTES =
                String.format("This account was created at %s.", LocalDateTime.now());
        public static final String TEST_ACC_PASSWORD = "super_secret";
        public static final String TEST_ACC_URL = "secret.org";
        public static final String TEST_ACC_USER = "test_user_99";
        public static final String TITLE_INPUT_ID = "#acc-edit-title-input";
        public static final String URL_INPUT_ID = "#acc-edit-url-input";
        public static final String USERNAME_INPUT_ID = "#acc-edit-username-input";
    }

    // Integration testing constants
    public static final class IntegrationTests {
        // To create DTOs
        private static final String ACC_NOTE_VALID = "Main account";
        private static final String ACC_PASSWORD_VALID = "password123";
        private static final String ACC_TITLE_GITHUB = "GitHub";
        private static final String ACC_TITLE_JIRA = "Jira";
        private static final String ACC_URL_VALID = "github.com";
        private static final String ACC_USERNAME_DEV = "developer";
        private static final int NONEXISTENT_ID = 10000;
        private static final int UNINITIALIZED_ID = -1;

        // To update fields
        public static final String ACC_NOTE_VALID_UPDATED = "Updated";
        public static final String ACC_PASSWORD_VALID_UPDATED = "password456";
        public static final String ACC_TITLE_GITHUB_UPDATED = "GitLab";
        public static final String ACC_URL_VALID_UPDATED = "gitlab.com";
        public static final String ACC_USERNAME_DEV_UPDATED = "dev2";

        // DTOs
        public static final AccountDTO ACCOUNT_DTO_GITHUB = new AccountDTO(UNINITIALIZED_ID, ACC_TITLE_GITHUB,
                ACC_USERNAME_DEV, ACC_PASSWORD_VALID, ACC_URL_VALID, ACC_NOTE_VALID);
        public static final AccountDTO ACCOUNT_DTO_JIRA = new AccountDTO(
                UNINITIALIZED_ID, ACC_TITLE_JIRA, ACC_USERNAME_DEV, ACC_PASSWORD_VALID, ACC_URL_VALID, ACC_NOTE_VALID);
        public static final AccountDTO ACCOUNT_DTO_NO_TITLE = new AccountDTO(
                UNINITIALIZED_ID, "", ACC_USERNAME_DEV, ACC_PASSWORD_VALID, ACC_URL_VALID, ACC_NOTE_VALID);
        // Never add this account to any test DB
        public static final AccountDTO NONEXISTENT_ACCOUNT = new AccountDTO(NONEXISTENT_ID, ACC_TITLE_GITHUB_UPDATED,
                ACC_USERNAME_DEV_UPDATED, ACC_PASSWORD_VALID_UPDATED, ACC_URL_VALID_UPDATED, ACC_NOTE_VALID_UPDATED);
    }

    // Accounts that we know the interactionCount value of
    public static final class MostUsedSortingTests {
        public static final Account LOW_USE = new Account.Builder()
                                                      .id(1)
                                                      .title("Low Use")
                                                      .username("lowuse")
                                                      .password("pwd")
                                                      .url("url")
                                                      .note("note")
                                                      .createdAt(BASE_TIME)
                                                      .lastInteract(BASE_TIME)
                                                      .interactionCount(5)
                                                      .isPinned(false)
                                                      .build();

        public static final Account HIGH_USE = new Account.Builder()
                                                       .id(2)
                                                       .title("High Use")
                                                       .username("highuse")
                                                       .password("pwd")
                                                       .url("url")
                                                       .note("note")
                                                       .createdAt(LocalDateTime.now())
                                                       .lastInteract(LocalDateTime.now())
                                                       .interactionCount(50)
                                                       .isPinned(false)
                                                       .build();
    }

    // Accounts that we know the isPinned value of, to test updating the pinned value
    public static final class PinningUpdateTests {
        public static final Account ACCOUNT_NOT_PINNED = new Account.Builder()
                                                                 .id(1)
                                                                 .title("title")
                                                                 .username("user")
                                                                 .password("pwd")
                                                                 .url("url")
                                                                 .note("note")
                                                                 .createdAt(BASE_TIME)
                                                                 .lastInteract(BASE_TIME)
                                                                 .interactionCount(3)
                                                                 .isPinned(false)
                                                                 .build();

        public static final Account ACCOUNT_PINNED = new Account.Builder()
                                                             .id(1)
                                                             .title("title")
                                                             .username("user")
                                                             .password("pwd")
                                                             .url("url")
                                                             .note("note")
                                                             .createdAt(BASE_TIME)
                                                             .lastInteract(BASE_TIME)
                                                             .interactionCount(3)
                                                             .isPinned(true)
                                                             .build();
    }

    // Constants for testing password generation
    public static final class PasswordGeneratorServiceTests {
        public static final String ALLOWED_GENERATED_PASSWORD_CHARACTERS =
                business.constants.Constants.PasswordGenerator.ALLOWED_CHARACTERS;
        public static final int SELECTED_PASSWORD_LENGTH = 24;
        public static final int VALID_PASSWORD_LENGTH = 16;
        public static final int MAX_PASSWORD_LENGTH = business.constants.Constants.MAX_PASSWORD_LEN;
    }

    // Accounts that we know what search text should filter what accounts
    public static final class SearchServiceTests {
        // Search text
        public static final String SAME_CASE = "Hoogle";
        public static final String DIFFERENT_CASE = "HooGle";
        public static final String USERNAME = "me2@HOOGLe.com";
        public static final String URL_PARTIAL = ".ca";
        public static final String NOTE_SECRET = "Secret";

        // Test accounts
        public static final Account ACCOUNT_HOOGLE_1 = new Account.Builder()
                                                               .id(1)
                                                               .title("Hoogle")
                                                               .username("me@hoogle.com")
                                                               .password("password")
                                                               .url("www.hoogle.com")
                                                               .note("Hoogle Account 1")
                                                               .createdAt(BASE_TIME)
                                                               .lastInteract(BASE_TIME)
                                                               .interactionCount(0)
                                                               .isPinned(false)
                                                               .build();

        public static final Account ACCOUNT_HOOGLE_2 = new Account.Builder()
                                                               .id(2)
                                                               .title("Hoogle")
                                                               .username("me2@hoogle.com")
                                                               .password("password")
                                                               .url("www.hoogle.com")
                                                               .note("Hoogle Account 2")
                                                               .createdAt(BASE_TIME.plusDays(1))
                                                               .lastInteract(BASE_TIME.plusDays(1))
                                                               .interactionCount(0)
                                                               .isPinned(false)
                                                               .build();

        public static final Account ACCOUNT_CHASEBOOK = new Account.Builder()
                                                                .id(3)
                                                                .title("Chasebook")
                                                                .username("chasebookuser")
                                                                .password("password")
                                                                .url("www.chasebook.com")
                                                                .note("My personal chasebook")
                                                                .createdAt(BASE_TIME.plusDays(2))
                                                                .lastInteract(BASE_TIME.plusDays(2))
                                                                .interactionCount(0)
                                                                .isPinned(false)
                                                                .build();

        public static final Account ACCOUNT_SECRET = new Account.Builder()
                                                             .id(4)
                                                             .title("Secret")
                                                             .username("sec")
                                                             .password("password")
                                                             .url("specialURL.ca")
                                                             .note("Secret")
                                                             .createdAt(BASE_TIME.plusDays(3))
                                                             .lastInteract(BASE_TIME.plusDays(3))
                                                             .interactionCount(0)
                                                             .isPinned(false)
                                                             .build();
    }

    // Accounts that we know the lexicographic ordering of
    public static final class TitleSortingTests {
        public static final Account ACC_AARDVARK = new Account.Builder()
                                                           .id(1)
                                                           .title("Aardvark")
                                                           .username("alpha")
                                                           .password("pwd")
                                                           .url("abc.com")
                                                           .note("Starts with Aa")
                                                           .createdAt(BASE_TIME.plusDays(1))
                                                           .lastInteract(BASE_TIME)
                                                           .interactionCount(0)
                                                           .isPinned(false)
                                                           .build();

        public static final Account ACC_APPLE = new Account.Builder()
                                                        .id(2)
                                                        .title("Apple")
                                                        .username("beta")
                                                        .password("pwd")
                                                        .url("apple.com")
                                                        .note("Starts with Ap, tests prefix sorting with Aardvark")
                                                        .createdAt(BASE_TIME.plusDays(2))
                                                        .lastInteract(BASE_TIME)
                                                        .interactionCount(0)
                                                        .isPinned(false)
                                                        .build();

        public static final Account ACC_APPLE_JUICE =
                new Account.Builder()
                        .id(3)
                        .title("Apple Juice")
                        .username("beta")
                        .password("pwd")
                        .url("apple.com/juice")
                        .note("Tests string extension/space sorting against 'Apple'")
                        .createdAt(BASE_TIME.plusDays(3))
                        .lastInteract(BASE_TIME)
                        .interactionCount(0)
                        .isPinned(false)
                        .build();

        public static final Account ACC_BANANA =
                new Account.Builder()
                        .id(4)
                        .title("banana") // Lowercase 'b' to test case-sensitivity (ASCII vs Natural sort)
                        .username("charlie")
                        .password("pwd")
                        .url("banana.com")
                        .note("Lowercase 'b' tests ASCII vs Case-Insensitive sorting")
                        .createdAt(BASE_TIME.plusDays(4))
                        .lastInteract(BASE_TIME)
                        .interactionCount(0)
                        .isPinned(false)
                        .build();
    }

    // Generic constants for unit tests
    public static final class UnitGenerics {
        // Does not exist
        public static final AccountDTO ACCOUNT_DTO_DNE = new AccountDTO(10000, "DNE", "DNE", "DNE", "DNE", "DNE");
    }
}
