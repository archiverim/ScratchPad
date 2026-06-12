package presentation.components;

import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import presentation.constants.Constants;

public abstract class LabeledLimitedTextInput extends VBox {
    /*** Instance variables ***/

    private final CharacterCounterLabel lengthLabel;
    private final LimitedTextInput inputControl;
    private final Label nameLabel;

    /*** Constructor ***/

    public LabeledLimitedTextInput(String nameLabel, int limit, TextInputControl inputControl) {
        this.nameLabel = new Label(nameLabel);
        this.inputControl = new LimitedTextInput(inputControl, limit);
        this.lengthLabel = new CharacterCounterLabel(this.inputControl.textProperty(), limit);
        setStyle();
        addChildren();
    }

    /*** Public methods ***/

    public String getText() {
        return inputControl.getText();
    }

    public TextInputControl getInput() {
        return inputControl.getControl();
    }

    /*** Protected methods ***/

    protected void setTextInputStyle(String className) {
        inputControl.addStyleClass(className);
    }

    /*** Private methods ***/

    private void addChildren() {
        this.getChildren().addAll(nameLabel, inputControl.getControl(), lengthLabel);
    }

    private void setStyle() {
        nameLabel.getStyleClass().add("account-edit-label");
        this.setSpacing(Constants.Layout.LABELLED_INPUT_SPACING);
    }
}
