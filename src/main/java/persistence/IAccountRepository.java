package persistence;

import java.util.List;

import models.Account;

public interface IAccountRepository {
    // ------- Create ------- //
    // Saves an account to the database.
    void saveAccount(Account account);

    // ------- Read ------- //
    // Will return a single account with the given id.
    Account getAccountById(int accountId);

    // Returns an ArrayList of all accounts in the database.
    List<Account> getAllAccounts();

    // ------- Update ------- //
    // Updates an existing account in the database.
    void updateAccount(Account account);

    // ------- Delete ------- //
    // Deletes the account with the given id from the database.
    void deleteAccount(int accountId);
}
