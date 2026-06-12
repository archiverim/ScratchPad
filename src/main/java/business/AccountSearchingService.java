package business;

import business.constants.Constants;
import java.util.List;
import models.Account;

public class AccountSearchingService {
    /**
     * Instance variables
     */

    private String searchText;

    /**
     * Constructor
     */

    public AccountSearchingService() {
        this.searchText = Constants.EMPTY_STRING;
    }

    /**
     * Public methods
     */

    // Sets the search text to be used for filtering accounts
    public void setSearchText(String searchText) {
        this.searchText = searchText.trim().toLowerCase();
    }

    // Filters the given list of accounts based on the current search text
    public List<Account> search(List<Account> accounts) {
        return accounts.stream().filter(a -> filterAccountByText(a, searchText)).toList();
    }

    /**
     * Private methods
     */

    // Helper method that does the filtering
    private boolean filterAccountByText(Account a, String text) {
        return a.getTitle().toLowerCase().contains(text) || a.getUsername().toLowerCase().contains(text)
                || a.getNote().toLowerCase().contains(text) || a.getUrl().toLowerCase().contains(text);
    }
}
