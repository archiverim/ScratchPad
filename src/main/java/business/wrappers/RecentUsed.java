package business.wrappers;

import models.Account;

public class RecentUsed extends AccountWrapper {
    /**
     * Constructor
     */

    public RecentUsed(Account account) {
        super(account);
    }

    /**
     * Public methods
     */

    @Override
    public int compareTo(AccountWrapper other) {
        return other.account.getLastInteract().compareTo(this.account.getLastInteract());
    }
}
