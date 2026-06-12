package business;

import business.constants.Constants;
import java.util.List;
import models.Account;
import persistence.IAccountRepository;

public class AccountCatalogService {
    /**
     * Instance variables
     */

    private IAccountRepository accountRepository;
    private AccountSortingService accountSortingService;
    private AccountSearchingService accountSearchingService;

    /**
     * Constructor
     */

    public AccountCatalogService(IAccountRepository accountRepository, AccountSortingService accountSortingService,
            AccountSearchingService accountSearchingService) {
        this.accountRepository = accountRepository;
        this.accountSortingService = accountSortingService;
        this.accountSearchingService = accountSearchingService;
    }

    /**
     * Public methods
     */

    // Returns a list of accounts, sorted and filtered based on the current
    // settings of the app
    public List<Account> getAccounts(int limit) {
        // Make sure limit >= 0
        int safeLimit = Math.max(0, limit);

        // Get all accounts
        List<Account> accounts = accountRepository.getAllAccounts();

        // Hand off to sorting and searching services to sort and filter the
        // accounts based on the current settings
        accounts = accountSortingService.sort(accounts);
        accounts = accountSearchingService.search(accounts);

        // Return the accounts, limited by the specified limit
        return accounts.stream().limit(safeLimit).toList();
    }

    // Return all accounts only limited by the app's max display limit
    public List<Account> getAccounts() {
        return this.getAccounts(Constants.MAX_ACCOUNTS_TO_DISPLAY);
    }
}
