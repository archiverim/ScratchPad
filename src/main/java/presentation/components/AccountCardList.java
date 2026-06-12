package presentation.components;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.Account;
import presentation.constants.Constants;

public class AccountCardList extends ScrollPane {
    private final Supplier<List<Account>> accountSource;
    private final Consumer<Account> onAccountEditSelect;
    private final Consumer<Account> onAccountDeleteSelect;
    private final BiConsumer<String, String> onCopySelect;
    private final Consumer<Account> onAccountPinSelect;
    private final Consumer<Integer> afterCopySelect;
    private final VBox accountsBox;

    public AccountCardList(Supplier<List<Account>> accountSource, Consumer<Account> onAccountEditSelect,
            Consumer<Account> onAccountDeleteSelect, BiConsumer<String, String> onCopySelect,
            Consumer<Account> onAccountPinSelect, Consumer<Integer> afterCopySelect) {
        this.accountSource = accountSource;
        this.onAccountEditSelect = onAccountEditSelect;
        this.onAccountDeleteSelect = onAccountDeleteSelect;
        this.onCopySelect = onCopySelect;
        this.onAccountPinSelect = onAccountPinSelect;
        this.afterCopySelect = afterCopySelect;

        this.accountsBox = new VBox(Constants.Layout.ACCOUNT_CARD_INBETWEEN_SPACING);
        this.accountsBox.getStyleClass().add("accounts-box");
        this.getStyleClass().add("account-card-list");
        this.setContent(accountsBox);
        this.setFitToWidth(true);

        this.refresh();
    }

    public void refresh() {
        clearAccounts();
        List<Account> accounts = getAccounts();

        if (accounts == null || accounts.isEmpty()) {
            Label emptyLabel = new Label(Constants.Text.NO_ACCOUNTS_TO_DISPLAY);
            this.accountsBox.getChildren().add(emptyLabel);
        } else {
            for (Account acc : accounts) {
                addAccount(acc);
            }
        }
    }

    private List<Account> getAccounts() {
        return accountSource.get();
    }

    public void addAccount(Account acc) {
        this.accountsBox.getChildren().add(new AccountCard(acc,
                (account)
                        -> { onAccountEditSelect.accept(account); },
                (account)
                        -> { onAccountDeleteSelect.accept(account); },
                onCopySelect, onAccountPinSelect, afterCopySelect));
    }

    public void clearAccounts() {
        this.accountsBox.getChildren().clear();
    }
}
