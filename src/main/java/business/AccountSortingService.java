package business;

import business.wrappers.AccountWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import models.Account;
import models.enums.SortingMode;

public class AccountSortingService {
    /**
     * Instance variables
     */

    private AccountSortingModeRegistry sortingModeRegistry;
    private SortingMode accountSortMode;

    /**
     * Constructor
     */

    public AccountSortingService(AccountSortingModeRegistry registry) {
        this.sortingModeRegistry = registry;
        this.setSortingMode(models.constants.Constants.SortingModes.DEFAULT_SORTING_MODE);
    }

    /**
     * Public methods
     */

    // Sets the sorting mode to be used for sorting accounts
    public void setSortingMode(SortingMode mode) {
        this.accountSortMode = mode;
    }

    // Returns a list of the available sorting modes
    public List<SortingMode> getSortingModes() {
        return sortingModeRegistry.getAvailableSortingModes();
    }

    // Returns a sorted list of accounts based on the current sorting mode of the
    // app
    public List<Account> sort(List<Account> accounts) {
        // Get the sorting wrapper object's consrtuctor from the registry
        Function<Account, AccountWrapper> constructor = sortingModeRegistry.getSortingWrapper(accountSortMode);

        // Wrap the Accounts in their sorting Wrapper
        List<AccountWrapper> sortableAccounts = wrapAccounts(accounts, constructor);

        // Sort the accounts
        Collections.sort(sortableAccounts);

        // Extract the Accounts
        List<Account> sortedAccounts = unwrapAccounts(sortableAccounts);

        return sortedAccounts;
    }

    /**
     * Private methods
     */

    // Helper methods to wrap accounts in their sorting wrappers
    private List<AccountWrapper> wrapAccounts(List<Account> accounts, Function<Account, AccountWrapper> constructor) {
        List<AccountWrapper> wrappedList = new ArrayList<>();
        for (Account account : accounts) {
            wrappedList.add(constructor.apply(account));
        }

        return wrappedList;
    }

    // Helper method to extract accounts from their wrappers after sorting
    private List<Account> unwrapAccounts(List<AccountWrapper> wrappedList) {
        List<Account> sortedAccounts = new ArrayList<>();
        for (AccountWrapper wrapper : wrappedList) {
            sortedAccounts.add(wrapper.getAccount());
        }

        return sortedAccounts;
    }
}
