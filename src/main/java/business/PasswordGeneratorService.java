package business;

import static business.constants.Constants.PasswordGenerator.ALLOWED_CHARACTERS;

import java.security.SecureRandom;

public class PasswordGeneratorService {
    /**
     * Instance variables
     */

    private final SecureRandom random;

    /**
     * Constructor
     */

    public PasswordGeneratorService() {
        this.random = new SecureRandom();
    }

    /**
     * Public methods
     */

    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int characterIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            password.append(ALLOWED_CHARACTERS.charAt(characterIndex));
        }

        return password.toString();
    }
}
