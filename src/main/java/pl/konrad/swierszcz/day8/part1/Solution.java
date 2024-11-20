package pl.konrad.swierszcz.day8.part1;

import java.util.List;

public class Solution {
    public static int getCharactersDifference(List<String> inputs) {
        return inputs.stream()
                .map(Solution::getStringLengthDifference)
                .mapToInt(i -> i)
                .sum();
    }

    private static int getStringLengthDifference(String input) {
        var directLength = input.length();
        String noSurroundingQuotes = noSurroundingQuotes(input);
        String noEscapedCharacters = noEscapedCharacters(noSurroundingQuotes);
        String noHexCharacters = noHexCharacters(noEscapedCharacters);

        return directLength - noHexCharacters.length();
    }

    private static String noSurroundingQuotes(String input) {
        return input.substring(1, input.length() - 1);
    }

    private static String noEscapedCharacters(String input) {
        return input.replaceAll("\\\\\"",".")
                .replaceAll("\\\\\\\\", ".");
    }

    private static String noHexCharacters(String input) {
        return input.replaceAll("\\\\x[a-zA-Z0-9]{2}", ".");
    }
}
