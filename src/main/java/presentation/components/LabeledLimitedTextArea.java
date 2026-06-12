package presentation.components;

import javafx.scene.control.TextArea;

public class LabeledLimitedTextArea extends LabeledLimitedTextInput {
    /*** Constructor ***/

    public LabeledLimitedTextArea(String nameLabel, int limit) {
        super(nameLabel, limit, new TextArea());
        setTextInputStyle();
    }

    public LabeledLimitedTextArea(String nameLabel, int limit, String textValue) {
        super(nameLabel, limit, new TextArea(textValue));
        setTextInputStyle();
    }

    /*** Private methods ***/

    private void setTextInputStyle() {
        super.setTextInputStyle("account-edit-textarea");
    }
}
