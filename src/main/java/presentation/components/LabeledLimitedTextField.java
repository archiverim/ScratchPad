package presentation.components;

import javafx.scene.control.TextField;

public class LabeledLimitedTextField extends LabeledLimitedTextInput {
    /*** Constructor ***/

    public LabeledLimitedTextField(String nameLabel, int limit) {
        super(nameLabel, limit, new TextField());
        setTextInputStyle();
    }

    public LabeledLimitedTextField(String nameLabel, int limit, String textValue) {
        super(nameLabel, limit, new TextField(textValue));
        setTextInputStyle();
    }

    /*** Private methods ***/

    private void setTextInputStyle() {
        super.setTextInputStyle("account-edit-input");
    }
}
