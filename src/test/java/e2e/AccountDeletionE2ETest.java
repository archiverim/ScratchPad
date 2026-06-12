package e2e;

import static constants.TestConstants.AssertionMessages.DATABASE_EMPTY_E2E_DELETE;
import static constants.TestConstants.EndToEndTests.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import application.OffGrid;
import javafx.stage.Stage;

public class AccountDeletionE2ETest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        // Initialize the application to connect database
        OffGrid app = new OffGrid();
        app.init();
        app.start(stage);
    }

    @Test
    public void testUserCanCancelAccountDeletion() {
        // Count how many accounts are currently on the screen
        int initialAccountCount = lookup(ACC_CARD_CLASS).queryAll().size();
        // Ensure there is at least one account to test with
        assertTrue(initialAccountCount > 0, DATABASE_EMPTY_E2E_DELETE);
        // Click on the delete button
        clickOn(ACC_CARD_DELETE_BUTTON_ID);
        // Click cancel on the confirmation alert
        clickOn(ACC_DELETE_CANCEL_DIALOG);
        // Verify that the account count did not change.
        int countAfterCancel = lookup(ACC_CARD_CLASS).queryAll().size();
        assertEquals(initialAccountCount, countAfterCancel);
    }
}
