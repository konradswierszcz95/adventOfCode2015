package pl.konrad.swierszcz.day5.part1;

import java.util.List;

public class Solution {
    private static final List<Character> requiredWolves = List.of('a', 'e', 'i', 'o', 'u');

    public static int countNiceStrings(List<String> input) {

    }

    private boolean isStringNice(String candidate) {
        return containsThreeWolves(candidate) && 
    }

    private boolean containsThreeWolves(String candidate) {
        return candidate.chars().filter(requiredWolves::contains).count() >= 3;
    }
}
