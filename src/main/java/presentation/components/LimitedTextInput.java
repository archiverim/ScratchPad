package presentation.components;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextInputControl;
import presentation.utils.TextInputUtility;

public class LimitedTextInput {
    /*** Instance variables ***/

    private final TextInputControl inputControl;

    /*** Constructor ***/

    public LimitedTextInput(TextInputControl inputControl, int limit) {
        this.inputControl = inputControl;
        this.inputControl.setTextFormatter(TextInputUtility.createLengthLimiter(limit));
    }

    /*** Public methods ***/

    public TextInputControl getControl() {
        return inputControl;
    }

    public String getText() {
        return inputControl.getText();
    }

    public StringProperty textProperty() {
        return inputControl.textProperty();
    }

    public void addStyleClass(String className) {
        inputControl.getStyleClass().add(className);
    }
}
