package application;

import business.AccountService;
import business.AccountSortingService;
import business.AccountCatalogService;
import business.AccountSearchingService;
import business.PasswordGeneratorService;
import business.AccountSortingModeRegistry;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import persistence.IAccountRepository;
import presentation.WindowManager;

public class OffGrid extends Application {
    private AccountService accountService;
    private AccountCatalogService accountCatalogService;
    private AccountSortingService accountSortingService;
    private AccountSearchingService accountSearchingService;
    private AccountSortingModeRegistry accountSortingModeRegistry;
    private PasswordGeneratorService passwordGeneratorService;
    private IAccountRepository accountRepository;

    /*** Public methods ***/

    // Runs on Application initialization
    @Override
    public void init() {
        // Setup dependencies to inject into the app
        DependencyInitializer dInitializer = DependencyInitializer.getInstance();
        this.accountRepository = dInitializer.createAccountRepository();
        this.accountService = dInitializer.createAccountService(accountRepository);

        this.accountSortingModeRegistry = dInitializer.createAccountSortingModeRegistry();
        this.accountSortingService = dInitializer.createAccountSortingService(accountSortingModeRegistry);
        this.accountSearchingService = dInitializer.createAccountSearchingService();
        this.passwordGeneratorService = dInitializer.createPasswordGeneratorService();
        this.accountCatalogService = dInitializer.createAccountCatalogService(
                accountRepository, accountSortingService, accountSearchingService);
    }

    // Runs on Application launch
    @Override
    public void start(Stage stage) {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-Regular.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-Bold.ttf"), 14);
        createWindowManager(stage);
    }

    // Creates our main window manager
    private void createWindowManager(Stage stage) {
        WindowManager wm = new WindowManager(stage, accountService, accountCatalogService, accountSortingService,
                accountSearchingService, passwordGeneratorService);
        wm.start();
    }
}
