package integration;

import static constants.TestConstants.*;
import static constants.TestConstants.AssertionMessages.DB_DELETION_FAILED;
import static constants.TestConstants.IntegrationTests.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.io.File;

import application.DependencyInitializer;
import business.exceptions.AccountDTOInvalidException;
import models.dtos.AccountDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import business.AccountService;
import business.AccountSortingService;
import business.AccountSortingModeRegistry;
import business.AccountCatalogService;
import business.AccountSearchingService;
import business.exceptions.AccountDoesNotExistException;
import business.exceptions.AccountValidationException;
import models.Account;
import persistence.IAccountRepository;
import persistence.IPasswordProtectedRelationalDatabase;
import persistence.RelationalDatabaseAccountRepository;
import persistence.SQLCipherRelationalDatabase;
import persistence.constants.DatabaseQueries.SQLCipher.Schema;

public class AccountServiceIntegrationTest {
    @TempDir private Path tempDir;

    private IPasswordProtectedRelationalDatabase database;
    private AccountService service;
    private AccountCatalogService catalogService;

    @BeforeEach
    public void setup() {
        Path databasePath = tempDir.resolve(RELATIONAL_DB_TEST_FILEPATH);
        database = new SQLCipherRelationalDatabase(databasePath.toString(), Schema.CREATE_ACCOUNTS_TABLE);
        database.connect(RELATIONAL_DB_TEST_PASSWORD);

        IAccountRepository repository = new RelationalDatabaseAccountRepository(database);
        service = new AccountService(repository);

        DependencyInitializer dependencyInitializer = DependencyInitializer.getInstance();
        AccountSortingModeRegistry sortingModeRegistry = dependencyInitializer.createAccountSortingModeRegistry();
        AccountSortingService sortingService = new AccountSortingService(sortingModeRegistry);
        AccountSearchingService searchingService = new AccountSearchingService();
        catalogService = new AccountCatalogService(repository, sortingService, searchingService);
    }

    @AfterEach
    public void teardown() throws SQLException {
        database.close();
        // Clean up the temporary database file
        File dbFile = tempDir.resolve(RELATIONAL_DB_TEST_FILEPATH).toFile();
        if (dbFile.exists()) {
            boolean deletionSuccess = dbFile.delete();
            assertTrue(deletionSuccess, DB_DELETION_FAILED);
        }
    }

    @Nested
    class CreateAccountTests {
        @Test
        public void saveAccountPersistsValidAccount() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            List<Account> accounts = catalogService.getAccounts();

            assertEquals(1, accounts.size());
            Account savedAccount = accounts.get(0);
            assertEquals(ACCOUNT_DTO_GITHUB.getTitle(), savedAccount.getTitle());
            assertEquals(ACCOUNT_DTO_GITHUB.getUsername(), savedAccount.getUsername());
            assertEquals(ACCOUNT_DTO_GITHUB.getPassword(), savedAccount.getPassword());
            assertEquals(ACCOUNT_DTO_GITHUB.getUrl(), savedAccount.getUrl());
            assertEquals(ACCOUNT_DTO_GITHUB.getNote(), savedAccount.getNote());
            assertNotNull(savedAccount.getCreatedAt());
            assertNotNull(savedAccount.getLastInteract());
        }

        @Test
        public void saveAccountRejectsInvalidFields() {
            assertThrows(AccountValidationException.class, () -> service.saveAccount(ACCOUNT_DTO_NO_TITLE));

            List<Account> accounts = catalogService.getAccounts();
            assertEquals(0, accounts.size());
        }

        @Test
        public void saveAccountAssignsUniqueIds() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            service.saveAccount(ACCOUNT_DTO_JIRA);

            List<Account> accounts = catalogService.getAccounts();

            assertEquals(2, accounts.size());
            assertNotEquals(accounts.get(0).getId(), accounts.get(1).getId());
        }

        @Test
        public void saveAccountGuardsNullDTO() {
            assertThrows(AccountDTOInvalidException.class, () -> service.saveAccount(null));
        }
    }

    @Nested
    class ReadAccountTests {
        @Test
        public void getAccountsReturnsPersistedAccounts() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            service.saveAccount(ACCOUNT_DTO_JIRA);

            List<Account> accounts = catalogService.getAccounts();

            assertEquals(2, accounts.size());
            assertTrue(containsTitle(accounts, ACCOUNT_DTO_GITHUB.getTitle()));
            assertTrue(containsTitle(accounts, ACCOUNT_DTO_JIRA.getTitle()));
        }
    }

    @Nested
    class UpdateAccountTests {
        @Test
        public void updateAccountPersistsChangedFields() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            Account savedAccount = catalogService.getAccounts().get(0);

            AccountDTO updated =
                    new AccountDTO(savedAccount.getId(), ACC_TITLE_GITHUB_UPDATED, ACC_USERNAME_DEV_UPDATED,
                            ACC_PASSWORD_VALID_UPDATED, ACC_URL_VALID_UPDATED, ACC_NOTE_VALID_UPDATED);

            service.updateAccount(updated);
            Account updatedAccount = catalogService.getAccounts().get(0);

            assertEquals(ACC_TITLE_GITHUB_UPDATED, updatedAccount.getTitle());
            assertEquals(ACC_USERNAME_DEV_UPDATED, updatedAccount.getUsername());
            assertEquals(ACC_PASSWORD_VALID_UPDATED, updatedAccount.getPassword());
            assertEquals(ACC_URL_VALID_UPDATED, updatedAccount.getUrl());
            assertEquals(ACC_NOTE_VALID_UPDATED, updatedAccount.getNote());
        }

        @Test
        public void updateAccountPreservesCreatedAt() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            Account savedAccount = catalogService.getAccounts().get(0);
            AccountDTO updated =
                    new AccountDTO(savedAccount.getId(), ACC_TITLE_GITHUB_UPDATED, ACC_USERNAME_DEV_UPDATED,
                            ACC_PASSWORD_VALID_UPDATED, ACC_URL_VALID_UPDATED, ACC_NOTE_VALID_UPDATED);

            service.updateAccount(updated);
            Account updatedAccount = catalogService.getAccounts().get(0);

            assertEquals(savedAccount.getCreatedAt(), updatedAccount.getCreatedAt());
        }

        @Test
        public void updateAccountIncrementsInteractionMetadata() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            Account savedAccount = catalogService.getAccounts().get(0);

            service.logInteraction(savedAccount.getId());
            Account updatedAccount = catalogService.getAccounts().get(0);

            assertEquals(savedAccount.getInteractionCount() + 1, updatedAccount.getInteractionCount());
            assertNotNull(updatedAccount.getLastInteract());
        }

        @Test
        public void updateMissingAccountThrows() {
            assertThrows(RuntimeException.class, () -> service.updateAccount(NONEXISTENT_ACCOUNT));
        }
    }

    @Nested
    class DeleteAccountTests {
        @Test
        public void deleteAccountRemovesPersistedAccount() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            Account savedAccount = catalogService.getAccounts().get(0);

            service.deleteAccount(savedAccount.getId());

            assertEquals(0, catalogService.getAccounts().size());
        }

        @Test
        public void deleteMissingAccountThrows() {
            assertThrows(AccountDoesNotExistException.class,
                    () -> service.deleteAccount(NONEXISTENT_ACCOUNT.getAccountId()));
        }

        @Test
        public void deleteOneAccountDoesNotDeleteOthers() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            service.saveAccount(ACCOUNT_DTO_JIRA);
            Account accountToDelete = findByTitle(catalogService.getAccounts(), ACCOUNT_DTO_GITHUB.getTitle());

            service.deleteAccount(accountToDelete.getId());
            List<Account> remainingAccounts = catalogService.getAccounts();

            assertEquals(1, remainingAccounts.size());
            assertEquals(ACCOUNT_DTO_JIRA.getTitle(), remainingAccounts.get(0).getTitle());
        }
    }

    @Nested
    class LogInteractionTests {
        @Test
        public void logingInteractionWhenAccountExists() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            Account accountToLog = findByTitle(catalogService.getAccounts(), ACCOUNT_DTO_GITHUB.getTitle());
            assertDoesNotThrow(() -> service.logInteraction(accountToLog.getId()));
            assertEquals(
                    1, findByTitle(catalogService.getAccounts(), ACCOUNT_DTO_GITHUB.getTitle()).getInteractionCount());
        }

        @Test
        public void logingInteractionWhenAccountDoesNotExist() {
            service.saveAccount(ACCOUNT_DTO_GITHUB);
            Account accountToLog = findByTitle(catalogService.getAccounts(), ACCOUNT_DTO_GITHUB.getTitle());
            assertThrows(AccountDoesNotExistException.class, () -> service.logInteraction(accountToLog.getId() + 1));
        }
    }

    private boolean containsTitle(List<Account> accounts, String title) {
        return accounts.stream().anyMatch(account -> account.getTitle().equals(title));
    }

    private Account findByTitle(List<Account> accounts, String title) {
        return accounts.stream().filter(account -> account.getTitle().equals(title)).findFirst().orElseThrow();
    }
}
