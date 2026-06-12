package presentation;

import business.AccountService;
import business.AccountSortingService;
import business.AccountCatalogService;
import business.AccountSearchingService;
import business.PasswordGeneratorService;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import presentation.controllers.DashboardController;
import presentation.constants.Constants;

import java.net.URL;

public class WindowManager implements INavigator {
    /*** Instance variables ***/

    private final AccountService accountService;
    private final AccountCatalogService accountCatalogService;
    private final AccountSortingService accountSortingService;
    private final AccountSearchingService accountSearchingService;
    private final PasswordGeneratorService passwordGeneratorService;
    private final Stage stage;

    /*** Constructor ***/

    public WindowManager(Stage stage, AccountService accountService, AccountCatalogService accountCatalogService,
            AccountSortingService accountSortingService, AccountSearchingService accountSearchingService,
            PasswordGeneratorService passwordGeneratorService) {
        this.stage = stage;
        this.accountService = accountService;
        this.accountCatalogService = accountCatalogService;
        this.accountSortingService = accountSortingService;
        this.accountSearchingService = accountSearchingService;
        this.passwordGeneratorService = passwordGeneratorService;
    }

    /*** Public methods ***/

    // Sets the scene to be the dashboard
    @Override
    public void navigateToDashboard() {
        if (accountService != null && accountCatalogService != null && accountSearchingService != null
                && passwordGeneratorService != null) {
            DashboardController dashboardController = new DashboardController(accountService, accountCatalogService,
                    accountSortingService, accountSearchingService, passwordGeneratorService);
            updateScene(dashboardController.getView());
        }
    }

    // Starts the app
    public void start() {
        navigateToDashboard();
        initialize();
        stage.show();
    }

    /*** Private methods ***/

    // Initializes the stage
    private void initialize() {
        stage.setTitle(Constants.Text.WINDOW_TITLE_LOGIN);
        linkStylesheet();
    }

    // Links the stylesheet
    private void linkStylesheet() {
        URL cssResource = getClass().getResource(Constants.Resources.STYLE_SHEET_PATH);
        if (cssResource != null && stage != null && stage.getScene() != null)
            stage.getScene().getStylesheets().add(cssResource.toExternalForm());
    }

    // Updates the scene to the passed in newRoot
    private void updateScene(Parent newRoot) {
        if (stage.getScene() == null) {
            Rectangle2D screen = Screen.getPrimary().getVisualBounds();
            double width = screen.getWidth() * Constants.Layout.APP_STARTING_RATIO;
            double height = screen.getHeight() * Constants.Layout.APP_STARTING_RATIO;
            stage.setScene(new Scene(newRoot, width, height));
        } else {
            stage.getScene().setRoot(newRoot);
        }
    }
}
