package unit;

import business.AccountValidator;
import business.PasswordGeneratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static constants.TestConstants.PasswordGeneratorServiceTests.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordGeneratorServiceTest {
    private PasswordGeneratorService passwordGeneratorService;

    @BeforeEach
    public void init() {
        passwordGeneratorService = new PasswordGeneratorService();
    }

    @Nested
    class GeneratePasswordTests {
        @Test
        public void generatePasswordSelectedLength() {
            // Act
            String password = passwordGeneratorService.generatePassword(SELECTED_PASSWORD_LENGTH);

            // Assert
            assertEquals(SELECTED_PASSWORD_LENGTH, password.length());
        }

        @Test
        public void generatePasswordOnlyUsesAllowedCharacters() {
            // Act
            String password = passwordGeneratorService.generatePassword(MAX_PASSWORD_LENGTH);

            // Assert
            assertTrue(containsOnlyAllowedCharacters(password));
        }

        @Test
        public void generatePasswordIsValidAccountPassword() {
            // Act
            String password = passwordGeneratorService.generatePassword(VALID_PASSWORD_LENGTH);

            // Assert
            assertDoesNotThrow(() -> AccountValidator.getInstance().validateAccountPassword(password));
        }

        private boolean containsOnlyAllowedCharacters(String password) {
            for (int i = 0; i < password.length(); i++) {
                if (ALLOWED_GENERATED_PASSWORD_CHARACTERS.indexOf(password.charAt(i)) == -1) {
                    return false;
                }
            }

            return true;
        }
    }
}
