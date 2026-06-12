package business;

import business.wrappers.AccountWrapper;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import models.Account;
import models.enums.SortingMode;

public class AccountSortingModeRegistry {
    /**
     * Instance variables
     */

    // Maps enum SortingMode to the constructor of the corresponding wrapper class
    private final Map<SortingMode, Function<Account, AccountWrapper>> registry = new EnumMap<>(SortingMode.class);

    /**
     * Public methods
     */

    // Registers a sorting mode with its corresponding wrapper constructor
    public void registerSortingMode(SortingMode mode, Function<Account, AccountWrapper> wrapperConstructor) {
        registry.put(mode, wrapperConstructor);
    }

    // Returns the sorting mode account wrapper for the given sorting mode
    public Function<Account, AccountWrapper> getSortingWrapper(SortingMode mode) {
        return registry.get(mode);
    }

    public List<SortingMode> getAvailableSortingModes() {
        return new ArrayList<>(registry.keySet());
    }
}
