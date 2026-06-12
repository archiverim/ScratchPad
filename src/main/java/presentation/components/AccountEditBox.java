package presentation.components;

import java.util.function.Supplier;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import presentation.constants.Constants;

public class AccountEditBox extends VBox {
    /*** Instance variables ***/

    private final int accountId;
    private final LabeledLimitedTextArea noteBox;
    private final LabeledLimitedTextField titleBox;
    private final LabeledLimitedTextField urlBox;
    private final LabeledLimitedTextField usernameBox;
    private final PasswordEditField passwordField;

    /*** Constructors ***/

    // For a new account addition
    public AccountEditBox(Supplier<String> onGeneratePassword) {
        accountId = -1;
        noteBox = new LabeledLimitedTextArea(Constants.Text.NOTES, Constants.Numbers.MAX_NOTE_INPUT_LEN);
        passwordField = new PasswordEditField(onGeneratePassword, false);
        titleBox = new LabeledLimitedTextField(Constants.Text.TITLE, Constants.Numbers.MAX_TITLE_LEN);
        urlBox = new LabeledLimitedTextField(Constants.Text.URL, Constants.Numbers.MAX_URL_LEN);
        usernameBox = new LabeledLimitedTextField(Constants.Text.USERNAME, Constants.Numbers.MAX_USERNAME_LEN);
        configureComponents();
        styleComponents();
        setTestIds();
    }

    // To edit an existing account that already has an ID.
    public AccountEditBox(int id, String title, String note, String password, String url, String username,
            Supplier<String> onGeneratePassword) {
        accountId = id;
        noteBox = new LabeledLimitedTextArea(Constants.Text.NOTES, Constants.Numbers.MAX_NOTE_INPUT_LEN, note);
        passwordField = new PasswordEditField(password, onGeneratePassword, true);
        titleBox = new LabeledLimitedTextField(Constants.Text.TITLE, Constants.Numbers.MAX_TITLE_LEN, title);
        urlBox = new LabeledLimitedTextField(Constants.Text.URL, Constants.Numbers.MAX_URL_LEN, url);
        usernameBox =
                new LabeledLimitedTextField(Constants.Text.USERNAME, Constants.Numbers.MAX_USERNAME_LEN, username);
        configureComponents();
        styleComponents();
        setTestIds();
    }

    /*** Public methods ***/

    public int getAccountId() {
        return accountId;
    }

    public String getNote() {
        return noteBox.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public String getTitle() {
        return titleBox.getText();
    }

    public String getUrl() {
        return urlBox.getText();
    }

    public String getUsername() {
        return usernameBox.getText();
    }

    /*** Private methods ***/

    private void configureComponents() {
        VBox passwordBox = passwordField.getView();
        this.getChildren().addAll(titleBox, usernameBox, passwordBox, urlBox, noteBox);
    }

    private void styleComponents() {
        this.setSpacing(Constants.Layout.COMPONENT_SPACING);
        VBox.setVgrow(noteBox, Priority.ALWAYS);
    }

    private void setTestIds() {
        passwordField.setTestId("acc-edit-password-input");
        titleBox.getInput().setId("acc-edit-title-input");
        usernameBox.getInput().setId("acc-edit-username-input");
        urlBox.getInput().setId("acc-edit-url-input");
        noteBox.getInput().setId("acc-edit-notes-input");
    }
}
