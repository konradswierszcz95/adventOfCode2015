package pl.konrad.swierszcz.day5.part2;

import java.util.List;

public class Solution {

    private Solution(){}

    public static long countNiceStrings(List<String> input) {
        return input.stream()
                .filter(Solution::isStringNice)
                .count();
    }

    private static boolean isStringNice(String candidate) {
        return containsTwoPairsOfLetters(candidate) && containsLetterSurroundedBySiblings(candidate);
    }

    private static boolean containsTwoPairsOfLetters(String candidate) {
        for (int i = 0; i < candidate.length() - 2; i++) {
            if (candidate.substring(i + 2).contains(candidate.substring(i, i + 2))) {
                return true;
            }
        }

        return false;
    }

    private static boolean containsLetterSurroundedBySiblings(String candidate) {
        for (int i = 0; i < candidate.length() - 2; i ++) {
            if (candidate.charAt(i) == candidate.charAt(i + 2)) {
                return true;
            }
        }

        return false;
    }

}
