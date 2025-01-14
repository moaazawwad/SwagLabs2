package Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DataGenerator {

    private static List<Integer> randomIndices = new ArrayList<>();
    private static int currentIndex = 0;

    // Generate random first name
    public static String generateFirstName() {
        return "FirstName" + new Random().nextInt(100);
    }

    // Generate random last name
    public static String generateLastName() {
        return "LastName" + new Random().nextInt(100);
    }

    // Generate random email
    public static String generateEmail() {
        return "user" + UUID.randomUUID() + "@example.com";
    }

    // Generate random telephone number
    public static String generateTelephone() {
        return "+2010" + (10000000 + new Random().nextInt(90000000));
    }

    // Generate random password
    public static String generatePassword() {
        return "Pass" + UUID.randomUUID().toString().substring(0, 6);
    }

    /**
     * Initialize the random index generator with the length of the element list.
     *
     * @param length The total number of elements (e.g., size of a list)
     */
    public static void initializeRandomIndexGenerator(int length) {
        randomIndices.clear();
        currentIndex = 0;
        for (int i = 0; i < length; i++) {
            randomIndices.add(i);
        }
        Collections.shuffle(randomIndices, new Random());
    }

    /**
     * Get a random index that hasn't been selected before.
     * Reshuffles and resets if all indices have been used.
     *
     * @return A unique random index
     */
    public static int getRandomIndex() {
        if (currentIndex >= randomIndices.size()) {
            currentIndex = 0; // Reset index
            Collections.shuffle(randomIndices, new Random());
        }
        return randomIndices.get(currentIndex++);
    }
}
