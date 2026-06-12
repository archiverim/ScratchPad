package application;

import application.constants.Constants;
import business.AccountService;
import business.AccountSortingModeRegistry;
import business.AccountSortingService;
import business.AccountSearchingService;
import business.AccountCatalogService;
import business.PasswordGeneratorService;
import business.wrappers.AlphabeticalTitleAsc;
import business.wrappers.AlphabeticalTitleDesc;
import business.wrappers.Favourites;
import business.wrappers.MostUsed;
import business.wrappers.Newest;
import business.wrappers.RecentUsed;
import models.enums.SortingMode;
import persistence.IAccountRepository;
import persistence.IPasswordProtectedRelationalDatabase;
import persistence.RelationalDatabaseAccountRepository;
import persistence.SQLCipherRelationalDatabase;
import persistence.constants.DatabaseQueries.SQLCipher.Schema;

// This class exists to generalize dependencies between the application and tests
public class DependencyInitializer {
    private static DependencyInitializer instance;

    private DependencyInitializer() {}

    public static DependencyInitializer getInstance() {
        if (instance == null) {
            instance = new DependencyInitializer();
        }
        return instance;
    }

    // Creates the account repository
    public IAccountRepository createAccountRepository() {
        IPasswordProtectedRelationalDatabase db = new SQLCipherRelationalDatabase(
                application.constants.Constants.DATABASE_PATH, Schema.CREATE_ACCOUNTS_TABLE);
        db.connect(Constants.DATABASE_KEY);
        return new RelationalDatabaseAccountRepository(db);
    }

    // Creates the account service (updates)
    public AccountService createAccountService(IAccountRepository repo) {
        AccountService accountService = new AccountService(repo);
        return accountService;
    }

    public AccountSortingModeRegistry createAccountSortingModeRegistry() {
        AccountSortingModeRegistry registry = new AccountSortingModeRegistry();

        // Add all sorting modes to the registry
        registry.registerSortingMode(SortingMode.MOST_USED_MODE, MostUsed::new);
        registry.registerSortingMode(SortingMode.RECENT_USED_MODE, RecentUsed::new);
        registry.registerSortingMode(SortingMode.NEWEST_MODE, Newest::new);
        registry.registerSortingMode(SortingMode.FAVOURITES_MODE, Favourites::new);
        registry.registerSortingMode(SortingMode.ALPHABETICAL_TITLE_ASC_MODE, AlphabeticalTitleAsc::new);
        registry.registerSortingMode(SortingMode.ALPHABETICAL_TITLE_DESC_MODE, AlphabeticalTitleDesc::new);

        return registry;
    }

    // Creates the account sorting service
    public AccountSortingService createAccountSortingService(AccountSortingModeRegistry registry) {
        AccountSortingService accountSortingService = new AccountSortingService(registry);
        return accountSortingService;
    }

    // Creates the account searching service
    public AccountSearchingService createAccountSearchingService() {
        AccountSearchingService searchingService = new AccountSearchingService();
        return searchingService;
    }

    // Creates account catalog service
    public AccountCatalogService createAccountCatalogService(
            IAccountRepository repo, AccountSortingService sortService, AccountSearchingService searchService) {
        AccountCatalogService catalogService = new AccountCatalogService(repo, sortService, searchService);
        return catalogService;
    }

    // Creates password generator service
    public PasswordGeneratorService createPasswordGeneratorService() {
        PasswordGeneratorService passwordGeneratorService = new PasswordGeneratorService();
        return passwordGeneratorService;
    }
}
