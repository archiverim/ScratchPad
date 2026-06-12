package business.wrappers;

import models.Account;

public class AlphabeticalTitleAsc extends AccountWrapper {
    /**
     * Constructor
     */

    public AlphabeticalTitleAsc(Account account) {
        super(account);
    }

    /**
     * Public methods
     */

    @Override
    public int compareTo(AccountWrapper other) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.account.getTitle(), other.account.getTitle());
    }
}
