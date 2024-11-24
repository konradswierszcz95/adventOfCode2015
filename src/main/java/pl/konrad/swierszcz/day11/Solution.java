package pl.konrad.swierszcz.day11;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    private static final List<String> FORBIDDEN_CHARACTERS = List.of("i", "o", "l");
    private static final Pattern GROUPS_OF_TWO_PATTERN = Pattern.compile("(.)\\1{2,}");

    public static String getNextCorrectPassword(String actualPassword) {
        String password = incrementPassword(actualPassword);

        while (!isPasswordCorrect(password)) {
            password = incrementPassword(password);
        }

        return password;
    }

    private static String incrementPassword(String password) {
        if (!notContainsForbiddenCharacter(password)) {
            return skipForbiddenLetter(password);
        }

        if (!hasTwoDifferentPairs(password)) {
            return setClosestPairs(password);
        }

        var characters = password.toCharArray();

        for (int i = characters.length - 1; i >= 0; i--) {
            characters[i] = incrementCharacter(characters[i]);

            if (characters[i] != 'a') {
                break;
            }

            if (i == 0) {
                i = characters.length - 1;
            }
        }

        return String.valueOf(characters);
    }

    private static String skipForbiddenLetter(String password) {
        var firstForbiddenIndex = FORBIDDEN_CHARACTERS.stream()
                .map(password::indexOf)
                .filter(i -> i >= 0)
                .mapToInt(i -> i)
                .min().orElseThrow(() -> new RuntimeException("Forbidden charecters not found"));

        var chars = password.toCharArray();
        chars[firstForbiddenIndex] = incrementCharacter(chars[firstForbiddenIndex]);

        for (int i = firstForbiddenIndex + 1; i < password.length(); i++) {
            chars[i] = 'a';
        }

        return String.valueOf(chars);
    }

    private static String setClosestPairs(String password) {
        var chars = password.toCharArray();

        char firstPairCharacter = chars[chars.length - 2] > chars[chars.length - 1] ? chars[chars.length - 2] : chars[chars.length - 1];
        chars[chars.length - 1] = firstPairCharacter;
        chars[chars.length - 2] = firstPairCharacter;

        if (hasTwoDifferentPairs(String.valueOf(chars))) {
            return String.valueOf(chars);
        }

        char secondPairCharacter = chars[chars.length - 4] > chars[chars.length - 3] ? chars[chars.length - 4] : chars[chars.length - 3];

        chars[chars.length - 3] = secondPairCharacter;
        chars[chars.length - 4] = secondPairCharacter;

        chars[chars.length - 1] = 'a';
        chars[chars.length - 2] = 'a';

        return String.valueOf(chars);
    }

    private static char incrementCharacter(char character) {
        if (character == 'z') {
            return 'a';
        }

        if (FORBIDDEN_CHARACTERS.contains(String.valueOf(character))) {
            return (char) (character + 2);
        }

        return (char) (character + 1);
    }

    private static boolean isPasswordCorrect(String password) {
        return hasThreeIncreasingLettersInRow(password) && notContainsForbiddenCharacter(password) && hasTwoDifferentPairs(password);
    }

    private static boolean hasThreeIncreasingLettersInRow(String password) {
        var chars = password.toCharArray();

        for (int i = 0; i < chars.length - 3; i++) {
            if (chars[i] + 1 == chars[i + 1] && chars[i] + 2 == chars[i + 2]) {
                return true;
            }
        }

        return false;
    }

    private static boolean notContainsForbiddenCharacter(String password) {
        return FORBIDDEN_CHARACTERS.stream()
                .noneMatch(password::contains);
    }

    private static boolean hasTwoDifferentPairs(String password) {
        Matcher match = GROUPS_OF_TWO_PATTERN.matcher(password);

        return match.namedGroups().size() >= 2;
    }
}
