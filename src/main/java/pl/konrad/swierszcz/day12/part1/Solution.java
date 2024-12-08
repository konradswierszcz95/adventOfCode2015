package pl.konrad.swierszcz.day12.part1;

import java.util.List;
import java.util.regex.Pattern;

public class Solution {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d{1,}");

    public static int getSumOfNumbers(List<String> input) {
        return input.stream()
                .mapToInt(Solution::getNumberSumRegex)
                .sum();
    }

    private static int getNumberSumRegex(String toSearch) {
        var matcher = NUMBER_PATTERN.matcher(toSearch);
        int sum = 0;

        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group());
        }

        return sum;
    }
}
