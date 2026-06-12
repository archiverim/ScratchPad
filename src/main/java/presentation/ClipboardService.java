package presentation;

import javafx.animation.PauseTransition;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.util.Duration;

public class ClipboardService {
    private static final Clipboard clipboard = Clipboard.getSystemClipboard();
    private static final ClipboardContent content = new ClipboardContent();

    private static void copyToClipboard(String text) {
        content.putString(text);
        clipboard.setContent(content);
    }

    private static void clearClipboard() {
        content.putString(null);
        clipboard.setContent(content);
    }

    public static void copyToClipboardWithTimeout(String text, int timeoutSeconds) {
        copyToClipboard(text);
        PauseTransition pause = new PauseTransition(Duration.seconds(timeoutSeconds));
        pause.setOnFinished(event -> {
            if (clipboard.getContent(DataFormat.PLAIN_TEXT) != null
                    && text.equals(clipboard.getContent(DataFormat.PLAIN_TEXT))) {
                clearClipboard();
            }
        });
        pause.play();
    }
}
