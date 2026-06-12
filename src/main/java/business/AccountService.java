package business;

import business.constants.Constants;
import business.exceptions.AccountDTOInvalidException;
import business.exceptions.AccountDoesNotExistException;
import business.exceptions.InvalidAccountIdException;
import java.time.LocalDateTime;
import models.Account;
import models.dtos.AccountDTO;
import persistence.IAccountRepository;
import persistence.exceptions.AccountNotFoundException;

public class AccountService {
    /**
     * Instance variables
     */

    private final IAccountRepository accountRepository;
    private final AccountValidator validator;

    /**
     * Constructor
     */

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.validator = AccountValidator.getInstance();
    }

    /**
     * Public methods
     */

    // Deletes an account from the database
    public void deleteAccount(int accountId) {
        if (accountId < Constants.MIN_ACCOUNT_ID) {
            throw new InvalidAccountIdException();
        }

        // validating that the account exists
        Account account = this.tryGetAccountById(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }

        try {
            accountRepository.deleteAccount(accountId);
        } catch (AccountNotFoundException e) {
            throw new AccountDoesNotExistException();
        }
    }

    // Updates an account
    public void updateAccount(AccountDTO accountDTO) {
        // validating rules
        validator.validateNewAccountFields(accountDTO.getTitle(), accountDTO.getUsername(), accountDTO.getPassword());

        // fetch the existing account
        Account oldAccount = this.tryGetAccountById(accountDTO.getAccountId());
        if (oldAccount == null) {
            throw new AccountDoesNotExistException();
        }

        Account updatedAccount = new Account.Builder()
                                         .id(accountDTO.getAccountId())
                                         .title(accountDTO.getTitle())
                                         .username(accountDTO.getUsername())
                                         .password(accountDTO.getPassword())
                                         .url(accountDTO.getUrl())
                                         .note(accountDTO.getNote())
                                         .createdAt(oldAccount.getCreatedAt())
                                         .lastInteract(LocalDateTime.now())
                                         .interactionCount(oldAccount.getInteractionCount())
                                         .isPinned(oldAccount.isPinned())
                                         .build();

        accountRepository.updateAccount(updatedAccount);
    }

    public void logInteraction(int accountId) {
        Account oldAccount = this.tryGetAccountById(accountId);
        if (oldAccount == null)
            throw new AccountDoesNotExistException();

        Account updatedAccount = new Account.Builder()
                                         .id(accountId)
                                         .title(oldAccount.getTitle())
                                         .username(oldAccount.getUsername())
                                         .password(oldAccount.getPassword())
                                         .url(oldAccount.getUrl())
                                         .note(oldAccount.getNote())
                                         .createdAt(oldAccount.getCreatedAt())
                                         .lastInteract(LocalDateTime.now())
                                         .interactionCount(oldAccount.getInteractionCount() + 1)
                                         .isPinned(oldAccount.isPinned())
                                         .build();

        accountRepository.updateAccount(updatedAccount);
    }

    public void togglePinnedAccount(int accountId) {
        Account oldAccount = this.tryGetAccountById(accountId);
        if (oldAccount == null) {
            throw new AccountDoesNotExistException();
        }

        Account updatedAccount = new Account.Builder()
                                         .id(accountId)
                                         .title(oldAccount.getTitle())
                                         .username(oldAccount.getUsername())
                                         .password(oldAccount.getPassword())
                                         .url(oldAccount.getUrl())
                                         .note(oldAccount.getNote())
                                         .createdAt(oldAccount.getCreatedAt())
                                         .lastInteract(LocalDateTime.now())
                                         .interactionCount(oldAccount.getInteractionCount())
                                         .isPinned(!oldAccount.isPinned())
                                         .build();

        accountRepository.updateAccount(updatedAccount);
    }

    public void saveAccount(AccountDTO accountDTO) {
        if (accountDTO == null)
            throw new AccountDTOInvalidException();

        validator.validateNewAccountFields(accountDTO.getTitle(), accountDTO.getUsername(), accountDTO.getPassword());

        Account newAccount = new Account.Builder()
                                     .title(accountDTO.getTitle())
                                     .username(accountDTO.getUsername())
                                     .password(accountDTO.getPassword())
                                     .url(accountDTO.getUrl())
                                     .note(accountDTO.getNote())
                                     .build();

        accountRepository.saveAccount(newAccount);
    }

    /**
     * Private methods
     */

    // Helper method to attempt to fetch an account by ID, returning null if not
    // found and catches exceptions
    private Account tryGetAccountById(int accountId) {
        try {
            return accountRepository.getAccountById(accountId);
        } catch (AccountNotFoundException e) {
            return null;
        }
    }
}
