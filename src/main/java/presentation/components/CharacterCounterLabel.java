package presentation.components;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import presentation.utils.TextInputUtility;

public class CharacterCounterLabel extends Label {
    /*** Constructor ***/

    public CharacterCounterLabel(StringProperty textProperty, int maxLength) {
        TextInputUtility.setupCharacterCounter(textProperty, this, maxLength);
        this.getStyleClass().add("character-counter-label");
    }
}
