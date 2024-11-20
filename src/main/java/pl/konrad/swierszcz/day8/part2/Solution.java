package pl.konrad.swierszcz.day8.part2;

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
        var missingCharactersForOutsideSurroundingQuotes = 2;

        return extendedEscapedCharacters(input).length() + missingCharactersForOutsideSurroundingQuotes - directLength;
    }

    private static String extendedEscapedCharacters(String input) {
        return input.replaceAll("\"", "..")
                .replaceAll("\\\\", "..");
    }
}
