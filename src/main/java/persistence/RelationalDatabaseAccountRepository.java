package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.Account;
import persistence.constants.Constants;
import persistence.constants.DatabaseQueries.SQLCipher.Queries;
import persistence.exceptions.AccountNotFoundException;

public class RelationalDatabaseAccountRepository implements IAccountRepository {
    private IPasswordProtectedRelationalDatabase database;
    private DateTimeFormatter formatter;

    /*** Constructor ***/
    public RelationalDatabaseAccountRepository(IPasswordProtectedRelationalDatabase database) {
        this.database = database;
        this.formatter = DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT);
    }

    /*** Public Methods ***/
    // Save a new account to the database
    @Override
    public void saveAccount(Account account) {
        try {
            database.executeUpdate(Queries.INSERT_ACCOUNT, account.getTitle(), account.getUsername(),
                    account.getPassword(), account.getUrl(), account.getNote(), account.isPinned());
        } catch (SQLException e) {
            throw new RuntimeException(Constants.ExceptionMessages.ACCOUNT_SAVE_FAILURE, e);
        }
    }

    // Retrieve an account by its ID
    @Override
    public Account getAccountById(int accountId) {
        try {
            ResultSet rs = database.executeQuery(Queries.SELECT_ACCOUNT_BY_ID, accountId);
            if (rs.next()) {
                return buildAccountModelFromResultSet(rs);
            } else {
                // The ResultSet is empty
                throw new AccountNotFoundException(
                        String.format(Constants.ExceptionMessages.ACCOUNT_NOT_FOUND, accountId));
            }
        } catch (SQLException e) {
            throw new AccountNotFoundException(String.format(Constants.ExceptionMessages.ACCOUNT_NOT_FOUND, accountId));
        }
    }

    // Get all accounts stored in the database
    @Override
    public List<Account> getAllAccounts() {
        try {
            ResultSet rs = database.executeQuery(Queries.SELECT_ALL_ACCOUNTS);
            ArrayList<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                Account account = buildAccountModelFromResultSet(rs);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            return new ArrayList<>(); // Return empty list if there's an error fetching accounts
        }
    }

    // Update an existing account in the database
    @Override
    public void updateAccount(Account account) {
        try {
            database.executeUpdate(Queries.UPDATE_ACCOUNT_BY_ID, account.getTitle(), account.getUsername(),
                    account.getPassword(), account.getUrl(), account.getNote(),
                    formatDateTimeForDatabase(account.getCreatedAt()),
                    formatDateTimeForDatabase(account.getLastInteract()), account.getInteractionCount(),
                    account.isPinned(), account.getId());
        } catch (SQLException e) {
            throw new AccountNotFoundException(
                    String.format(Constants.ExceptionMessages.ACCOUNT_NOT_FOUND_FOR_UPDATE, account.getId()));
        }
    }

    // Delete an account from the database by its ID
    @Override
    public void deleteAccount(int accountId) {
        try {
            int rowsDeleted = database.executeUpdate(Queries.DELETE_ACCOUNT_BY_ID, accountId);
            if (rowsDeleted == 0) {
                throw new AccountNotFoundException(
                        String.format(Constants.ExceptionMessages.ACCOUNT_NOT_FOUND_FOR_DELETION, accountId));
            }
        } catch (SQLException e) {
            throw new AccountNotFoundException(
                    String.format(Constants.ExceptionMessages.ACCOUNT_NOT_FOUND_FOR_DELETION, accountId));
        }
    }

    /*** Helper Methods ***/

    // Format LocalDateTime to String for database storage
    private String formatDateTimeForDatabase(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

    // Build an Account model from a ResultSet row
    private Account buildAccountModelFromResultSet(ResultSet rs) throws SQLException {
        LocalDateTime createdAt = null;
        LocalDateTime lastInteract = null;

        // Extract datetime
        String createdAtStr = rs.getString(Constants.AccountFieldNames.ACCOUNT_CREATED_AT);
        if (createdAtStr != null) {
            createdAt = LocalDateTime.parse(createdAtStr, formatter);
        }
        String lastInteractStr = rs.getString(Constants.AccountFieldNames.ACCOUNT_LAST_INTERACT);
        if (lastInteractStr != null) {
            lastInteract = LocalDateTime.parse(lastInteractStr, formatter);
        }

        return new Account.Builder()
                .id(rs.getInt(Constants.AccountFieldNames.ACCOUNT_ID))
                .title(rs.getString(Constants.AccountFieldNames.ACCOUNT_TITLE))
                .username(rs.getString(Constants.AccountFieldNames.ACCOUNT_USERNAME))
                .password(rs.getString(Constants.AccountFieldNames.ACCOUNT_PASSWORD_DB))
                .url(rs.getString(Constants.AccountFieldNames.ACCOUNT_URL))
                .note(rs.getString(Constants.AccountFieldNames.ACCOUNT_NOTE))
                .createdAt(createdAt)
                .lastInteract(lastInteract)
                .interactionCount(rs.getInt(Constants.AccountFieldNames.ACCOUNT_INTERACTION_COUNT))
                .isPinned(rs.getInt(Constants.AccountFieldNames.ACCOUNT_PINNED) == 1)
                .build();
    }
}
