import java.security.SecureRandom;

public class PasswordGenerator {
    private final int length;
    private final String uppercase;
    private final String lowercase;
    private final String digits;
    private final String specialCharacters;
    protected final String allCharacters;
    protected final SecureRandom random;

    public PasswordGenerator(int length) {
        this(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz", "0123456789", "!@#$%^&*()-_=+{}[]|;:<>,.?/");
    }

    public PasswordGenerator(int length, String uppercase, String lowercase, String digits, String specialCharacters) {
        this.length = length;
        this.uppercase = uppercase;
        this.lowercase = lowercase;
        this.digits = digits;
        this.specialCharacters = specialCharacters;
        this.allCharacters = uppercase + lowercase + digits + specialCharacters;
        this.random = new SecureRandom();
    }

    public String generatePassword() {
        StringBuilder password = new StringBuilder(length);
        // Ensure the password contains at least one character from each required set
        password.append(uppercase.charAt(random.nextInt(uppercase.length())));
        password.append(lowercase.charAt(random.nextInt(lowercase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
        // Fill the remaining characters
        for (int i = 4; i < length; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        // Shuffle the characters to avoid predictable patterns
        return shuffleString(password.toString());
    }

    protected String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
} 