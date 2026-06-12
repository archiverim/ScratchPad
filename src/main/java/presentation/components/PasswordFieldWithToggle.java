package presentation.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.javafx.FontIcon;

import javafx.beans.property.StringProperty;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import presentation.constants.Constants;
import presentation.utils.TextInputUtility;

public class PasswordFieldWithToggle extends HBox {
    private final PasswordField hiddenPasswordField;
    private final TextField visiblePasswordField;
    private final ToggleButton showPasswordButton;
    private boolean selectionDisabled;

    public PasswordFieldWithToggle() {
        hiddenPasswordField = new PasswordField();
        visiblePasswordField = new TextField();
        showPasswordButton = new ToggleButton();

        configureFields();
        configureButton();
        bindPasswordFields();
        configureLayout();
    }

    public PasswordFieldWithToggle(String password) {
        this();
        setText(password);
    }

    public String getText() {
        return hiddenPasswordField.getText();
    }

    public void setText(String password) {
        hiddenPasswordField.setText(password);
    }

    public void setTestId(String testId) {
        hiddenPasswordField.setId(testId);
        visiblePasswordField.setId(testId);
    }

    public StringProperty textProperty() {
        return hiddenPasswordField.textProperty();
    }

    public void setLengthLimit(int maxLength) {
        hiddenPasswordField.setTextFormatter(TextInputUtility.createLengthLimiter(maxLength));
        visiblePasswordField.setTextFormatter(TextInputUtility.createLengthLimiter(maxLength));
    }

    public void setEditable(boolean editable) {
        hiddenPasswordField.setEditable(editable);
        visiblePasswordField.setEditable(editable);
    }

    public void setSelectionDisabled(boolean selectionDisabled) {
        this.selectionDisabled = selectionDisabled;
        hiddenPasswordField.setFocusTraversable(!selectionDisabled);
        visiblePasswordField.setFocusTraversable(!selectionDisabled);
        clearSelection();
    }

    public void addFieldStyleClass(String styleClass) {
        hiddenPasswordField.getStyleClass().add(styleClass);
        visiblePasswordField.getStyleClass().add(styleClass);
    }

    public void addToggleButtonStyleClass(String styleClass) {
        showPasswordButton.getStyleClass().add(styleClass);
    }

    private void configureFields() {
        visiblePasswordField.setVisible(false);
        visiblePasswordField.setManaged(false);
        hiddenPasswordField.setMaxWidth(Double.MAX_VALUE);
        visiblePasswordField.setMaxWidth(Double.MAX_VALUE);

        hiddenPasswordField.selectedTextProperty().addListener((obs, oldSelection, newSelection) -> clearSelection());
        visiblePasswordField.selectedTextProperty().addListener((obs, oldSelection, newSelection) -> clearSelection());
    }

    private void configureButton() {
        showPasswordButton.setGraphic(new FontIcon(Constants.IconNames.EYE_CLOSED));
        showPasswordButton.setTooltip(new Tooltip(Constants.Text.SHOW_HIDE_PASSWORD));
        showPasswordButton.setOnAction(e -> {
            if (showPasswordButton.isSelected()) {
                showPasswordButton.setGraphic(new FontIcon(Constants.IconNames.EYE_OPEN));
            } else {
                showPasswordButton.setGraphic(new FontIcon(Constants.IconNames.EYE_CLOSED));
            }
        });
    }

    private void bindPasswordFields() {
        visiblePasswordField.managedProperty().bind(showPasswordButton.selectedProperty());
        visiblePasswordField.visibleProperty().bind(showPasswordButton.selectedProperty());
        hiddenPasswordField.managedProperty().bind(showPasswordButton.selectedProperty().not());
        hiddenPasswordField.visibleProperty().bind(showPasswordButton.selectedProperty().not());
        hiddenPasswordField.styleProperty().bindBidirectional(visiblePasswordField.styleProperty());
        visiblePasswordField.textProperty().bindBidirectional(hiddenPasswordField.textProperty());
    }

    private void configureLayout() {
        StackPane fieldStack = new StackPane();

        fieldStack.getChildren().addAll(hiddenPasswordField, visiblePasswordField);
        HBox.setHgrow(fieldStack, Priority.ALWAYS);

        StackPane.setAlignment(showPasswordButton, Pos.CENTER_RIGHT);
        StackPane.setMargin(showPasswordButton, new Insets(Constants.INSETS.ZERO, Constants.INSETS.EIGHT, Constants.INSETS.ZERO, Constants.INSETS.ZERO));
        fieldStack.getChildren().add(showPasswordButton);

        this.getChildren().add(fieldStack);
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    private void clearSelection() {
        if (selectionDisabled) {
            hiddenPasswordField.deselect();
            visiblePasswordField.deselect();
        }
    }
}
