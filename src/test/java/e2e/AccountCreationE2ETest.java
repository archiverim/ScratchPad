package e2e;

import static constants.TestConstants.EndToEndTests.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import application.OffGrid;
import javafx.stage.Stage;

public class AccountCreationE2ETest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        OffGrid app = new OffGrid();
        app.init();
        app.start(stage);
    }

    @Test
    public void testUserCanAddNewAccount() {
        // Initial count of accounts
        int initialCount = lookup(ACC_CARD_CLASS).queryAll().size();

        // Click the + button to show the add form
        clickOn(ADD_ACC_BUTTON_ID);

        // Type a title
        clickOn(TITLE_INPUT_ID);
        write(TEST_ACC_NAME);
        // Type a username
        clickOn(USERNAME_INPUT_ID);
        write(TEST_ACC_USER);
        // Type a password
        clickOn(PASSWORD_INPUT_ID);
        write(TEST_ACC_PASSWORD);
        // Type a url
        clickOn(URL_INPUT_ID);
        write(TEST_ACC_URL);
        // Type notes
        clickOn(NOTES_INPUT_ID);
        write(TEST_ACC_NOTES);

        // Click the Add button at the bottom to save the account
        clickOn(CREATE_ACC_BUTTON_ID);

        // Verify the new account card was added
        int newCount = lookup(ACC_CARD_CLASS).queryAll().size();
        assertEquals(initialCount + 1, newCount);
    }
}
