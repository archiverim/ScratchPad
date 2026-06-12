package business.wrappers;

import models.Account;

public abstract class AccountWrapper implements Comparable<AccountWrapper> {
    /**
     * Instance variables
     */

    protected final Account account;

    /**
     * Constructor
     */

    public AccountWrapper(Account account) {
        this.account = account;
    }

    /**
     * Public methods
     */

    public Account getAccount() {
        return account;
    }
}
