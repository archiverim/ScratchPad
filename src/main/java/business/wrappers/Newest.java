package business.wrappers;

import models.Account;

public class Newest extends AccountWrapper {
    /**
     * Constructor
     */

    public Newest(Account account) {
        super(account);
    }

    /**
     * Public methods
     */

    @Override
    public int compareTo(AccountWrapper other) {
        return other.account.getCreatedAt().compareTo(this.account.getCreatedAt());
    }
}
