package presentation.components;

import java.util.function.Supplier;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import presentation.ClipboardService;
import presentation.constants.*;

public class PasswordEditField {
    /*** Instance variables ***/

    private final Label passwordLabel;
    private final CharacterCounterLabel lengthLabel;
    private final PasswordFieldWithToggle passwordField;
    private final Button copyClipboardButton;
    private final Button generatePasswordButton;
    private final Supplier<String> onGeneratePassword;
    private final boolean isEditMode;
    private VBox passwordBox;

    /*** Constructor ***/

    public PasswordEditField(Supplier<String> onGeneratePassword, boolean isEditMode) {
        this.onGeneratePassword = onGeneratePassword;
        this.isEditMode = isEditMode;
        passwordField = new PasswordFieldWithToggle();
        copyClipboardButton = new Button();
        generatePasswordButton = new Button();
        passwordLabel = new Label(Constants.Text.PASSWORD);
        lengthLabel = new CharacterCounterLabel(passwordField.textProperty(), Constants.Numbers.MAX_PASSWORD_LEN);
        setupButtons();
        setEventListeners();
        configureLengthLimit();
        configureBox();
        styleComponents();
    }

    public PasswordEditField(String password, Supplier<String> onGeneratePassword, boolean isEditMode) {
        this(onGeneratePassword, isEditMode);
        passwordField.setText(password);
    }

    /*** Public methods ***/

    public String getText() {
        return passwordField.getText();
    }

    public VBox getView() {
        return passwordBox;
    }

    public void setTestId(String testId) {
        passwordField.setTestId(testId);
    }

    /*** Private methods ***/

    private void configureBox() {
        HBox passwordHBox = new HBox(passwordField, copyClipboardButton, generatePasswordButton);
        passwordHBox.setSpacing(Constants.Layout.COMPONENT_SPACING);
        passwordHBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(passwordField, Priority.ALWAYS);
        passwordBox = new VBox(passwordLabel, passwordHBox, lengthLabel);
        passwordBox.setSpacing(Constants.Layout.LABELLED_INPUT_SPACING);
    }

    private void configureLengthLimit() {
        passwordField.setLengthLimit(Constants.Numbers.MAX_PASSWORD_LEN);
    }

    private void setEventListeners() {
        setCopyClipboardButtonClick();
        setGeneratePasswordButtonClick();
    }

    private void styleComponents() {
        passwordLabel.getStyleClass().add("account-edit-label");
        passwordField.addFieldStyleClass("account-edit-input");
        passwordField.addToggleButtonStyleClass("account-edit-button");
        copyClipboardButton.getStyleClass().add("account-edit-button");
        generatePasswordButton.getStyleClass().add("password-generator-button");
    }

    private void setGeneratePasswordButtonClick() {
        generatePasswordButton.setOnAction(e -> {
            if (onGeneratePassword != null) {
                passwordField.setText(onGeneratePassword.get());
            }
        });
    }

    private void setCopyClipboardButtonClick() {
        copyClipboardButton.setOnAction(e -> {
            ClipboardService.copyToClipboardWithTimeout(passwordField.getText(), Constants.Numbers.COPY_TIMEOUT);
        });
    }

    private void setupButtons() {
        boolean showGeneratePasswordButton = !isEditMode;
        generatePasswordButton.setVisible(showGeneratePasswordButton);
        generatePasswordButton.setManaged(showGeneratePasswordButton);
        generatePasswordButton.setTooltip(new Tooltip(Constants.Text.GENERATE_PASSWORD));
        generatePasswordButton.setGraphic(new FontIcon(Constants.IconNames.GENERATE));
        copyClipboardButton.setGraphic(new FontIcon(Constants.IconNames.COPY));
        copyClipboardButton.setTooltip(new Tooltip(Constants.Text.COPY_TO_CLIPBOARD));
    }
}
