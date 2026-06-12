package presentation.components;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import presentation.utils.DateTimeFormatter;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import models.Account;
import presentation.constants.Constants;

public class AccountCard extends VBox {
    private final Account account;
    private final Consumer<Account> onAccountEditSelect;
    private final Consumer<Account> onAccountDeleteSelect;
    private final BiConsumer<String, String> onCopySelect;
    private final Consumer<Account> onAccountPinSelect;
    private final Consumer<Integer> afterCopySelect;

    public AccountCard(Account acc, Consumer<Account> onAccountEditSelect, Consumer<Account> onAccountDeleteSelect,
            BiConsumer<String, String> onCopySelect, Consumer<Account> onAccountPinSelect,
            Consumer<Integer> afterCopySelect) {
        super(Constants.Layout.COMPONENT_SPACING);
        this.account = acc;
        this.onAccountEditSelect = onAccountEditSelect;
        this.onAccountDeleteSelect = onAccountDeleteSelect;
        this.onCopySelect = onCopySelect;
        this.onAccountPinSelect = onAccountPinSelect;
        this.afterCopySelect = afterCopySelect;

        this.getChildren().addAll(createTopRow(acc), createNameRow(acc), createPasswordSection(acc),
                createUrlSection(acc), createMetadataSection(acc));

        this.getStyleClass().add("account-card");
        this.setMaxWidth(Double.MAX_VALUE);
    }

    private HBox createTopRow(Account acc) {
        Label title = new Label(acc.getTitle());
        title.getStyleClass().add("field-title");

        Button editButton = createEditButton();
        bindEditButton(editButton);

        FontIcon star = createPinIcon();

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox topRow = new HBox(Constants.Layout.COMPONENT_SPACING, title, spacer, editButton, star);
        topRow.setAlignment(Pos.CENTER_LEFT);
        topRow.setMaxWidth(Double.MAX_VALUE);
        return topRow;
    }

    private HBox createNameRow(Account acc) {
        FontIcon userIcon = new FontIcon(FontAwesomeSolid.USER);
        userIcon.getStyleClass().add("field-icon");

        Label username = new Label(acc.getUsername());
        username.getStyleClass().add("username-label");

        Button copyButton = createCopyButton(username.getText(), Constants.Text.USERNAME);

        HBox nameRow = new HBox(Constants.Layout.HBOX_SPACING, username, copyButton);
        nameRow.setAlignment(Pos.CENTER_LEFT);
        nameRow.getStyleClass().add("field-row");
        return nameRow;
    }

    private HBox createPasswordSection(Account acc) {
        Label passwordLabel = new Label(Constants.Text.PASSWORD);
        passwordLabel.getStyleClass().add("username-label");

        PasswordFieldWithToggle passwordField = createPasswordField(acc);
        Button copyButton = createCopyButton(acc.getPassword(), Constants.Text.PASSWORD);
        Button deleteButton = createDeleteButton();
        bindDeleteButton(deleteButton);

        HBox.setHgrow(passwordField, Priority.ALWAYS);
        passwordField.setMaxWidth(Double.MAX_VALUE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox passwordRow =
                new HBox(Constants.Layout.HBOX_SPACING, passwordLabel, passwordField, copyButton, spacer, deleteButton);
        passwordRow.setAlignment(Pos.CENTER_LEFT);
        passwordRow.getStyleClass().add("field-row");

        return passwordRow;
    }

    private HBox createUrlSection(Account acc) {
        String url = acc.getUrl() == null ? "" : acc.getUrl();
        boolean hasUrl = !url.isBlank();

        Button copyButton = createCopyButton(acc.getUrl(), Constants.Text.URL);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        if (!hasUrl) {
            HBox emptyRow = new HBox();
            emptyRow.getStyleClass().add("field-row");
            return emptyRow;
        }

        Label urlLabel = new Label(Constants.Text.URL);
        urlLabel.getStyleClass().add("username-label");

        Label urlValue = new Label(url);
        urlValue.getStyleClass().add("url-value");
        FontIcon linkIcon = new FontIcon(FontAwesomeSolid.LINK);
        linkIcon.getStyleClass().add("field-icon");

        HBox urlRow = new HBox(Constants.Layout.COMPONENT_SPACING, urlLabel, linkIcon, urlValue, copyButton);
        urlRow.setAlignment(Pos.CENTER_LEFT);
        urlRow.getStyleClass().add("field-row");

        return urlRow;
    }

    private HBox createMetadataSection(Account acc) {
        Label creationDate = new Label(String.join(
                " ", Constants.Text.DATE_CREATED_LABEL, DateTimeFormatter.FormatLocalDateTime(acc.getCreatedAt())));
        Label interact = new Label(
                String.join(" ", Constants.Text.INTERACTION_COUNT_LABEL, String.valueOf(acc.getInteractionCount())));
        HBox container = new HBox(creationDate, interact);
        container.setSpacing(Constants.Layout.ACCOUNT_CARD_INBETWEEN_SPACING);
        container.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(container, Priority.ALWAYS);
        return container;
    }

    private Button createCopyButton(String textToCopy, String fieldName) {
        Button copyButton = new Button();
        copyButton.setGraphic(new FontIcon(Constants.IconNames.COPY));
        copyButton.setTooltip(new Tooltip(Constants.Text.COPY_TO_CLIPBOARD));
        copyButton.getStyleClass().add("icon-only-button");

        copyButton.setOnAction(e -> {
            if (onCopySelect != null) {
                onCopySelect.accept(textToCopy, fieldName);
            }
            if (afterCopySelect != null) {
                afterCopySelect.accept(account.getId());
            }
        });

        return copyButton;
    }

    private Button createDeleteButton() {
        Button deleteButton = new Button(Constants.Text.DELETE);
        deleteButton.getStyleClass().add("account-delete-button");
        deleteButton.setId("account-card-delete-btn");
        return deleteButton;
    }

    private void bindDeleteButton(Button deleteButton) {
        deleteButton.setOnAction(e -> {
            if (onAccountDeleteSelect != null) {
                onAccountDeleteSelect.accept(account);
            }
        });
    }

    private PasswordFieldWithToggle createPasswordField(Account acc) {
        PasswordFieldWithToggle passwordField = new PasswordFieldWithToggle(acc.getPassword());
        passwordField.setEditable(false);
        passwordField.setSelectionDisabled(true);
        passwordField.addFieldStyleClass("password-field");
        passwordField.addToggleButtonStyleClass("icon-only-button");
        return passwordField;
    }

    private Button createEditButton() {
        Button editButton = new Button();
        editButton.setGraphic(new FontIcon(FontAwesomeSolid.EDIT));
        editButton.getStyleClass().add("edit-button");
        return editButton;
    }

    private void bindEditButton(Button editButton) {
        editButton.setOnAction(e -> {
            if (onAccountEditSelect != null) {
                onAccountEditSelect.accept(account);
            }
        });
    }

    private FontIcon createPinIcon() {
        FontIcon star = new FontIcon(account.isPinned() ? FontAwesomeSolid.STAR : FontAwesomeRegular.STAR);
        star.setIconSize(Constants.Layout.STAR_SIZE);
        star.setOnMouseClicked(e -> {
            if (onAccountPinSelect != null) {
                onAccountPinSelect.accept(account);
            }
        });
        return star;
    }
}
