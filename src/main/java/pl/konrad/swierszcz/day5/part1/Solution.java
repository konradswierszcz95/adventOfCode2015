package pl.konrad.swierszcz.day5.part1;

import java.util.List;

public class Solution {
    private static final List<Character> requiredWolves = List.of('a', 'e', 'i', 'o', 'u');
    private static final List<String> naughtyWords = List.of("ab", "cd", "pq", "xy");

    private Solution(){}

    public static long countNiceStrings(List<String> input) {
        return input.stream()
                .filter(Solution::isStringNice)
                .count();
    }

    private static boolean isStringNice(String candidate) {
        return containsAtLeastThreeWolves(candidate) && containsDoubledLetter(candidate) && notContainsNaughtyElements(candidate);
    }

    private static boolean containsAtLeastThreeWolves(String candidate) {
        return candidate.chars().mapToObj(i -> (char) i)
                .filter(requiredWolves::contains).count() >= 3;
    }

    private static boolean containsDoubledLetter(String candidate) {
        var candidateChars = candidate.toCharArray();
        for (int i = 0; i < candidateChars.length - 1; i++) {
            if (candidateChars[i] == candidateChars[i+1]) {
                return true;
            }
        }

        return false;
    }

    private static boolean notContainsNaughtyElements(String candidate) {
        var naughtyElements = naughtyWords.stream()
                .filter(candidate::contains)
                .toList();

        return naughtyElements.isEmpty();
    }
}
