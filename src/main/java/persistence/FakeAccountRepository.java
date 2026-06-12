package persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Account;
import persistence.exceptions.*;
import persistence.constants.*;

public class FakeAccountRepository implements IAccountRepository {
    private HashMap<Integer, Account> accounts;
    private int idCounter; // The next free id to be assigned to an account on creation.

    public FakeAccountRepository() {
        this.accounts = new HashMap<>();
        this.idCounter = 0;
    }

    @Override
    public void saveAccount(Account account) {
        LocalDateTime now = LocalDateTime.now();
        Account accountToSave = new Account.Builder()
                                        .id(idCounter)
                                        .title(account.getTitle())
                                        .username(account.getUsername())
                                        .password(account.getPassword())
                                        .url(account.getUrl())
                                        .note(account.getNote())
                                        .createdAt(now)
                                        .lastInteract(now)
                                        .interactionCount(0)
                                        .isPinned(account.isPinned())
                                        .build();
        idCounter++;
        accounts.put(accountToSave.getId(), accountToSave);
    }

    @Override
    public Account getAccountById(int accountId) throws AccountNotFoundException {
        if (!accounts.containsKey(accountId)) {
            throw new AccountNotFoundException(String.format(Constants.ExceptionMessages.ACCOUNT_NOT_FOUND, accountId));
        }
        return accounts.get(accountId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public void updateAccount(Account account) throws AccountNotFoundException {
        if (!accounts.containsKey(account.getId())) {
            throw new AccountNotFoundException(
                    String.format(Constants.ExceptionMessages.ACCOUNT_NOT_FOUND_FOR_UPDATE, account.getId()));
        }
        accounts.put(account.getId(), account);
    }

    @Override
    public void deleteAccount(int accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new AccountNotFoundException(
                    String.format(Constants.ExceptionMessages.ACCOUNT_NOT_FOUND_FOR_DELETION, accountId));
        }
        accounts.remove(accountId);
    }

    public void seedData() {
        // Adds some demo accounts
        Account account1 = new Account.Builder()
                                   .id(1)
                                   .title("GitHub")
                                   .username("developer@example.com")
                                   .password("8x!vF9$2kLp")
                                   .url("github.com")
                                   .note("Main account for open source repositories")
                                   .createdAt(LocalDateTime.now().minusDays(45))
                                   .lastInteract(LocalDateTime.now().minusDays(2))
                                   .interactionCount(0)
                                   .isPinned(true)
                                   .build();

        Account account2 = new Account.Builder()
                                   .id(2)
                                   .title("Stack Overflow")
                                   .username("code_ninja")
                                   .password("bX4#9mCq@wEr")
                                   .url("stackoverflow.com")
                                   .note("Used for programming Q&A")
                                   .createdAt(LocalDateTime.now().minusDays(12))
                                   .lastInteract(LocalDateTime.now().minusDays(12))
                                   .interactionCount(15)
                                   .isPinned(false)
                                   .build();

        Account account3 = new Account.Builder()
                                   .id(3)
                                   .title("AWS Console")
                                   .username("cloud_admin")
                                   .password("zT2*8vNp&yHj")
                                   .url("aws.amazon.com")
                                   .note("Web hosting and database administration")
                                   .createdAt(LocalDateTime.now().minusHours(5))
                                   .lastInteract(LocalDateTime.now())
                                   .interactionCount(8)
                                   .isPinned(false)
                                   .build();

        this.saveAccount(account1);
        this.saveAccount(account2);
        this.saveAccount(account3);
    }
}
