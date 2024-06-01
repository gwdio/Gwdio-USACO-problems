import java.util.Random;

public class RandomStringGenerator {
    public static void main(String[] args) {
        char targetLetter = 'A'; // The letter for which an even count is desired
        int length = 10; // Length of the generated string

        String randomString = generateRandomStringWithEvenCount(targetLetter, length);
        System.out.println(randomString);
    }

    public static String generateRandomStringWithEvenCount(char targetLetter, int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        int targetCount = random.nextInt(length / 2) * 2;

        for (int i = 0; i < length; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            if (randomChar == targetLetter && targetCount > 0) {
                sb.append(randomChar);
                targetCount--;
            } else {
                sb.append(randomChar);
            }
        }

        return sb.toString();
    }
}
