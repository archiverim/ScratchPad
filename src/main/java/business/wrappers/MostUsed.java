package business.wrappers;

import models.Account;

public class MostUsed extends AccountWrapper {
    /**
     * Constructor
     */

    public MostUsed(Account account) {
        super(account);
    }

    /**
     * Public methods
     */

    @Override
    public int compareTo(AccountWrapper other) {
        return Integer.compare(other.account.getInteractionCount(), this.account.getInteractionCount());
    }
}
