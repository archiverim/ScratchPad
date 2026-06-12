package business.wrappers;

import models.Account;

public class Favourites extends AccountWrapper {
    /**
     * Constructor
     */

    public Favourites(Account account) {
        super(account);
    }

    /**
     * Public methods
     */

    @Override
    public int compareTo(AccountWrapper other) {
        return Boolean.compare(other.account.isPinned(), this.account.isPinned());
    }
}
