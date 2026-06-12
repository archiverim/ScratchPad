package presentation.utils;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;

// Static methods to provide commonly used functionality to TextInputControls
public class TextInputUtility {
    // Create a formatter to only allow content changes if the new text is less than or equal to the maxLength
    public static TextFormatter<String> createLengthLimiter(int maxLength) {
        return new TextFormatter<>(change -> {
            if (change.isContentChange() && change.getControlNewText().length() > maxLength) {
                return null;
            }
            return change;
        });
    }

    public static void setupCharacterCounter(StringProperty textProperty, Label lengthLabel, int maxLength) {
        lengthLabel.textProperty().bind(Bindings.createStringBinding(() -> {
            String text = textProperty.get();
            int currentLength = text == null ? 0 : text.length();
            return currentLength + " / " + maxLength;
        }, textProperty));
    }
}
