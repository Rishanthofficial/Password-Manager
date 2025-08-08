import java.security.SecureRandom;

public class StrongPasswordGenerator extends PasswordGenerator {
    public StrongPasswordGenerator() {
        super(12); // Always at least 12 characters
    }

    @Override
    public String generatePassword() {
        // Ensure at least one of each required character type, then fill the rest
        StringBuilder password = new StringBuilder(12);
        password.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        password.append("abcdefghijklmnopqrstuvwxyz".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyz".length())));
        password.append("0123456789".charAt(random.nextInt("0123456789".length())));
        password.append("!@#$%^&*()-_=+{}[]|;:<>,.?/".charAt(random.nextInt("!@#$%^&*()-_=+{}[]|;:<>,.?/".length())));
        for (int i = 4; i < 12; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        return shuffleString(password.toString());
    }
} 