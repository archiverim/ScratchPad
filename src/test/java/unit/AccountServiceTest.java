package unit;

import java.util.ArrayList;
import java.util.List;

import static constants.TestConstants.PinningUpdateTests.ACCOUNT_NOT_PINNED;
import static constants.TestConstants.PinningUpdateTests.ACCOUNT_PINNED;
import static constants.TestConstants.DeleteUpdateTests.TO_DELETE;
import static constants.TestConstants.UnitGenerics.ACCOUNT_DTO_DNE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import business.AccountService;
import business.exceptions.AccountDoesNotExistException;
import business.exceptions.InvalidAccountIdException;
import models.Account;

import org.mockito.junit.jupiter.MockitoExtension;
import persistence.IAccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Nested
    class DeleteAccountTests {
        private class DeleteStubRepo extends StubAccountRepository {
            public Account accountToReturn;

            public DeleteStubRepo() {
                super(new ArrayList<>());
            }

            @Override
            public Account getAccountById(int accountId) {
                return accountToReturn;
            }
        }

        @Test
        public void deleteNegativeAccount() {
            // Create a stub repository to return the accounts
            IAccountRepository stubRepo = new StubAccountRepository(new ArrayList<>());
            AccountService service = new AccountService(stubRepo);

            assertThrows(InvalidAccountIdException.class, () -> service.deleteAccount(-5));
        }

        @Test
        public void deleteAccount() {
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(TO_DELETE);

            DeleteStubRepo stubRepo = new DeleteStubRepo();
            stubRepo.accountToReturn = TO_DELETE; // The account we delete gets returned when the repo is queries for it

            AccountService service = new AccountService(stubRepo);

            assertDoesNotThrow(() -> service.deleteAccount(TO_DELETE.getId()));
        }

        @Test
        public void deleteNonexistentAccount() {
            DeleteStubRepo stubRepo = new DeleteStubRepo();
            AccountService service = new AccountService(stubRepo);
            assertThrows(AccountDoesNotExistException.class, () -> service.deleteAccount(1));
        }
    }

    @Nested
    class UpdateAccountTests {
        private class UpdateStubRepo extends StubAccountRepository {
            public Account accountToReturn;

            public UpdateStubRepo() {
                super(new ArrayList<>());
            }

            @Override
            public Account getAccountById(int accountId) {
                return accountToReturn;
            }
        }

        private UpdateStubRepo trackingRepo;
        private AccountService service;

        @BeforeEach
        public void setup() {
            trackingRepo = new UpdateStubRepo();
            service = new AccountService(trackingRepo);
        }

        @Test // Make sure an error is thrown when a non-existing/null account is deleted
        public void throwsWhenAccountDoesNotExist() {
            trackingRepo.accountToReturn = null; // The account we delete won't exist

            // Account doesn't exist so it should throw
            assertThrows(AccountDoesNotExistException.class, () -> service.updateAccount(ACCOUNT_DTO_DNE));
        }
    }

    @Nested
    class TogglePinnedAccountTests {
        @Mock private IAccountRepository mockRepo;

        private AccountService service;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);
            service = new AccountService(mockRepo);
        }

        @Test
        public void pinsUnpinnedAccount() {
            when(mockRepo.getAccountById(1)).thenReturn(ACCOUNT_NOT_PINNED);

            service.togglePinnedAccount(1);

            ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
            verify(mockRepo).updateAccount(accountCaptor.capture());
            Account updatedAccount = accountCaptor.getValue();

            assertEquals(ACCOUNT_NOT_PINNED.getId(), updatedAccount.getId());
            assertTrue(updatedAccount.isPinned());
        }

        @Test
        public void unpinsPinnedAccount() {
            when(mockRepo.getAccountById(1)).thenReturn(ACCOUNT_PINNED);
            service.togglePinnedAccount(1);

            ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
            verify(mockRepo).updateAccount(accountCaptor.capture());
            Account updatedAccount = accountCaptor.getValue();

            assertEquals(ACCOUNT_PINNED.getId(), updatedAccount.getId());
            assertFalse(updatedAccount.isPinned());
        }

        @Test
        public void throwsWhenAccountDoesNotExist() {
            when(mockRepo.getAccountById(ACCOUNT_DTO_DNE.getAccountId())).thenReturn(null);

            assertThrows(AccountDoesNotExistException.class,
                    () -> service.togglePinnedAccount(ACCOUNT_DTO_DNE.getAccountId()));
            verify(mockRepo, never()).updateAccount(any(Account.class));
        }
    }

    private static class StubAccountRepository implements IAccountRepository {
        private final ArrayList<Account> accounts;

        public StubAccountRepository(List<Account> accounts) {
            this.accounts = new ArrayList<>(accounts);
        }

        @Override
        public void saveAccount(Account account) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Account getAccountById(int accountId) {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<Account> getAllAccounts() {
            return new ArrayList<>(accounts);
        }

        @Override
        public void updateAccount(Account account) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void deleteAccount(int accountId) {
            return;
        }
    }
}
