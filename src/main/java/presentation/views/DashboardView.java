package presentation.views;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import models.enums.SortingMode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import models.Account;
import presentation.components.AccountCardList;
import models.dtos.AccountDTO;
import presentation.components.AddAccountPane;
import presentation.components.SearchFilterBox;
import presentation.constants.Constants;

public class DashboardView extends GridPane {
    private final AccountCardList accountListBox;
    private final HBox sidePanel;
    private final SearchFilterBox searchFilterBox;

    public DashboardView(Supplier<List<Account>> accountSource, Consumer<Account> onAccountEditSelect,
            Consumer<Account> onAccountDeleteSelect, BiConsumer<String, String> onCopySelect,
            Consumer<Account> onAccountPinSelect, Consumer<SortingMode> onSortChange, Runnable onAddAccountClick,
            Consumer<String> onSearchClick, List<SortingMode> sortingModes, Consumer<Integer> afterCopySelect) {
        AccountCardList accountList = new AccountCardList(accountSource, onAccountEditSelect, onAccountDeleteSelect,
                onCopySelect, onAccountPinSelect, afterCopySelect);
        accountListBox = accountList;
        searchFilterBox = new SearchFilterBox(onSortChange, onAddAccountClick, onSearchClick, sortingModes);
        sidePanel = new HBox();

        initLayout();
        buildSections();
        setStyles();
        displayPlaceholder();
    }

    private void initLayout() {
        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(Constants.Layout.DASHBOARD_MAIN_COL_WIDTH);
        mainColumn.setHgrow(Priority.ALWAYS);

        ColumnConstraints sidebarColumn = new ColumnConstraints();
        sidebarColumn.setPercentWidth(Constants.Layout.DASHBOARD_SIDE_COL_WIDTH);

        RowConstraints topRow = new RowConstraints();
        topRow.setPercentHeight(Constants.Layout.DASHBOARD_TOP_ROW_HEIGHT);

        RowConstraints bottomRow = new RowConstraints();
        bottomRow.setPercentHeight(Constants.Layout.DASHBOARD_BOTTOM_ROW_HEIGHT);
        bottomRow.setVgrow(Priority.ALWAYS);

        this.getColumnConstraints().addAll(mainColumn, sidebarColumn);
        this.getRowConstraints().addAll(topRow, bottomRow);

        GridPane.setVgrow(accountListBox, Priority.ALWAYS);
        GridPane.setVgrow(sidePanel, Priority.ALWAYS);
    }

    private void buildSections() {
        this.add(searchFilterBox, Constants.INSETS.ZERO, Constants.INSETS.ZERO, Constants.INSETS.TWO,
                Constants.INSETS.ONE);
        this.add(accountListBox, Constants.INSETS.ZERO, Constants.INSETS.ONE);
        this.add(sidePanel, Constants.INSETS.ONE, Constants.INSETS.ONE);
    }

    private void setStyles() {
        this.getStyleClass().add("dashboard");
        searchFilterBox.getStyleClass().add("dashboard-top-bar");
        accountListBox.getStyleClass().add("dashboard-main");
        sidePanel.getStyleClass().add("dashboard-side");

        sidePanel.setAlignment(Pos.CENTER);
        sidePanel.setFillHeight(true);
        sidePanel.setPadding(new Insets(Constants.INSETS.TWENTY4));
    }

    private AddAccountPane createAddAccountPane(boolean isEditMode, Account accountToEdit, Runnable onCancel,
            Consumer<AccountDTO> onSave, Supplier<String> onGeneratePassword) {
        return new AddAccountPane(onCancel, onSave, isEditMode, accountToEdit, onGeneratePassword);
    }

    public void displayPlaceholder() {
        sidePanel.getChildren().clear();

        Label placeholder = new Label(Constants.Text.DYNAMIC_BOX_PLACEHOLDER);
        placeholder.getStyleClass().add("placeholder-label");
        placeholder.setWrapText(true);
        placeholder.setMaxWidth(Double.MAX_VALUE);
        placeholder.setMaxHeight(Double.MAX_VALUE);
        placeholder.setAlignment(Pos.CENTER);
        VBox.setVgrow(placeholder, Priority.ALWAYS);

        sidePanel.getChildren().add(placeholder);
    }

    public void refreshAccountList() {
        accountListBox.refresh();
    }

    public void displayAccountPane(boolean isEditMode, Account accountToEdit, Runnable onCancel,
            Consumer<AccountDTO> onSave, Supplier<String> onGeneratePassword) {
        AddAccountPane addAccountPane =
                createAddAccountPane(isEditMode, accountToEdit, onCancel, onSave, onGeneratePassword);
        sidePanel.getChildren().clear();
        sidePanel.getChildren().add(addAccountPane);
    }
}
