package presentation.controllers;

import business.AccountCatalogService;
import business.AccountSearchingService;
import business.AccountService;
import business.AccountSortingService;
import business.PasswordGeneratorService;
import models.enums.SortingMode;
import business.exceptions.AccountDoesNotExistException;
import business.exceptions.AccountValidationException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Account;
import presentation.ClipboardService;
import models.dtos.AccountDTO;
import presentation.constants.Constants;
import presentation.views.DashboardView;

public class DashboardController {
    private final AccountService accountService;
    private final DashboardView dashboardView;
    private final AccountSortingService accountSortingService;
    private final AccountSearchingService accountSearchingService;
    private final PasswordGeneratorService passwordGeneratorService;

    public DashboardController(AccountService accountService, AccountCatalogService accountCatalogService,
            AccountSortingService accountSortingService, AccountSearchingService accountSearchingService,
            PasswordGeneratorService passwordGeneratorService) {
        this.accountService = accountService;
        this.accountSortingService = accountSortingService;
        this.accountSearchingService = accountSearchingService;
        this.passwordGeneratorService = passwordGeneratorService;
        this.dashboardView = new DashboardView(accountCatalogService::getAccounts, this::onAccountEditSelect,
                this::onAccountDeleteSelect, this::onCopySelect, this::onAccountPinSelect, this::onSortChange,
                this::onAddAccountClick, this::onSearchClick, this.accountSortingService.getSortingModes(),
                this::afterCopySelect);
    }

    public DashboardView getView() {
        return dashboardView;
    }

    private void onAccountCancel() {
        dashboardView.displayPlaceholder();
    }

    private void onSaveNewAccount(AccountDTO formData) {
        try {
            accountService.saveAccount(formData);
            dashboardView.refreshAccountList();
            dashboardView.displayPlaceholder();
        } catch (AccountValidationException e) {
            throw new RuntimeException(e.getMessage()); // Pass the specific error up
        } catch (RuntimeException e) {
            throw new RuntimeException(Constants.ExceptionMessages.UNEXPECTED);
        }
    }

    private void onSaveEditAccount(AccountDTO formData) {
        try {
            accountService.updateAccount(formData);
            accountService.logInteraction(formData.getAccountId());
            dashboardView.refreshAccountList();
            dashboardView.displayPlaceholder();
        } catch (AccountValidationException | AccountDoesNotExistException e) {
            throw new RuntimeException(e.getMessage()); // Pass the specific error up
        } catch (RuntimeException e) {
            throw new RuntimeException(Constants.ExceptionMessages.UNEXPECTED);
        }
    }

    private void onAccountEditSelect(Account account) {
        dashboardView.displayAccountPane(
                true, account, this::onAccountCancel, this::onSaveEditAccount, this::onGeneratePassword);
    }

    private void onAccountDeleteSelect(Account account) {
        // create confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Constants.Text.DELETE_CONFIRMATION_TITLE);
        alert.setHeaderText(Constants.Text.DELETE_CONFIRMATION_SUBTITLE);
        alert.setContentText(Constants.Text.DELETE_CONFIRMATION_PROMPT);

        // Wait for the user's response
        // If delete is confirmed, delete account and refresh list
        // If canceled, close the popup and do nothing
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                accountService.deleteAccount(account.getId());
                dashboardView.refreshAccountList();
            }
        });
    }

    private void onCopySelect(String textToCopy, String fieldName) {
        ClipboardService.copyToClipboardWithTimeout(textToCopy, Constants.Numbers.COPY_TIMEOUT);

        // Show success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Constants.Text.COPY_SUCCESS_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(
                String.format(Constants.Text.COPY_SUCCESS_MESSAGE, fieldName, Constants.Numbers.COPY_TIMEOUT));
        alert.showAndWait();
    }

    private void onSearchClick(String searchText) {
        accountSearchingService.setSearchText(searchText);
        dashboardView.refreshAccountList();
    }

    private void onAccountPinSelect(Account account) {
        accountService.togglePinnedAccount(account.getId());
        accountService.logInteraction(account.getId());
        dashboardView.refreshAccountList();
    }

    private void onSortChange(SortingMode sortMode) {
        accountSortingService.setSortingMode(sortMode);
        dashboardView.refreshAccountList();
    }

    private void onAddAccountClick() {
        dashboardView.displayAccountPane(
                false, null, this::onAccountCancel, this::onSaveNewAccount, this::onGeneratePassword);
    }

    private String onGeneratePassword() {
        return passwordGeneratorService.generatePassword(Constants.Numbers.GENERATED_PASSWORD_LENGTH);
    }

    private void afterCopySelect(int accountId) {
        accountService.logInteraction(accountId);
        dashboardView.refreshAccountList();
    }
}
