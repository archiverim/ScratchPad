package presentation.components;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import models.Account;
import models.dtos.AccountDTO;
import presentation.constants.Constants;

public class AddAccountPane extends VBox {
    /*** Instance variables ***/

    private final AccountEditBox accountEditBox;
    private final HBox buttonBox;
    private final Button cancelButton;
    private final Button saveButton;
    private final Label errorLabel;
    private final Label headerLabel;
    private final Runnable onCancel;
    private final Consumer<AccountDTO> onSave;

    /*** Constructor ***/

    public AddAccountPane(Runnable onCancel, Consumer<AccountDTO> onSave, boolean isEditMode, Account accountToEdit,
            Supplier<String> onGeneratePassword) {
        if (isEditMode && accountToEdit != null) {
            accountEditBox = new AccountEditBox(accountToEdit.getId(), accountToEdit.getTitle(),
                    accountToEdit.getNote(), accountToEdit.getPassword(), accountToEdit.getUrl(),
                    accountToEdit.getUsername(), onGeneratePassword);
        } else {
            accountEditBox = new AccountEditBox(onGeneratePassword);
        }
        this.cancelButton = new Button(Constants.Text.CANCEL);
        this.saveButton = new Button(isEditMode ? Constants.Text.SAVE : Constants.Text.CREATE);
        this.errorLabel = new Label();
        this.headerLabel = new Label(isEditMode ? Constants.Text.EDIT_HEADER : Constants.Text.ADD_HEADER);
        this.onCancel = onCancel;
        this.onSave = onSave;
        buttonBox = new HBox();
        configureComponents();
        styleComponents();
        bindEventHandlers();
        setTestIds();
    }

    /*** Private methods ***/

    private void bindEventHandlers() {
        // set the save button
        setSaveButtonClick();
        // set the cancel button
        setCancelButtonClick();
    }

    private void configureBox() {
        buttonBox.getChildren().addAll(saveButton, cancelButton);
        this.getChildren().addAll(headerLabel, makeSeparator(), accountEditBox, errorLabel, makeSeparator(), buttonBox);
    }

    // Adds Icons and Tooltips to buttons
    private void configureButtons() {
        saveButton.setGraphic(new FontIcon(Constants.IconNames.SAVE));
        cancelButton.setGraphic(new FontIcon(Constants.IconNames.CANCEL));
    }

    private void configureComponents() {
        configureButtons();
        configureBox();
    }

    private void styleComponents() {
        cancelButton.getStyleClass().add("account-pill-button");
        saveButton.getStyleClass().add("account-pill-button");
        buttonBox.setSpacing(Constants.Layout.COMPONENT_SPACING);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        errorLabel.getStyleClass().add("error-label");
        errorLabel.setWrapText(true);
        errorLabel.setVisible(false);
        headerLabel.getStyleClass().add("add-account-header");
        this.setSpacing(Constants.Layout.COMPONENT_SPACING);
        VBox.setVgrow(accountEditBox, Priority.ALWAYS);
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    private Separator makeSeparator() {
        Separator s = new Separator();
        s.getStyleClass().add("horizontal-divider");
        return s;
    }

    private void setSaveButtonClick() {
        saveButton.setOnAction(e -> {
            try {
                AccountDTO formData = getFormData();
                onSave.accept(formData);
            } catch (RuntimeException ex) {
                setErrorMessage(ex.getMessage());
                errorLabel.setVisible(true);
            }
        });
    }

    private void setCancelButtonClick() {
        cancelButton.setOnAction(e -> {
            if (onCancel != null) {
                onCancel.run();
            }
        });
    }

    private AccountDTO getFormData() {
        return new AccountDTO(accountEditBox.getAccountId(), accountEditBox.getTitle(), accountEditBox.getUsername(),
                accountEditBox.getPassword(), accountEditBox.getUrl(), accountEditBox.getNote());
    }

    private void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    private void setTestIds() {
        saveButton.setId("create-account-btn");
    }
}
