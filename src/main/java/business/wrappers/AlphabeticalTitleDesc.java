package business.wrappers;

import models.Account;

public class AlphabeticalTitleDesc extends AccountWrapper {
    /**
     * Constructor
     */

    public AlphabeticalTitleDesc(Account account) {
        super(account);
    }

    /**
     * Public methods
     */

    @Override
    public int compareTo(AccountWrapper other) {
        return String.CASE_INSENSITIVE_ORDER.compare(other.account.getTitle(), this.account.getTitle());
    }
}
