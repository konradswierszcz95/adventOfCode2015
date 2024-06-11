package pl.konrad.swierszcz.day1.part1;

import java.util.stream.Collectors;

public class Solution {
    public static long countFloor(String input) {
        var parts = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.partitioningBy(c -> c.equals('(')));

        return parts.get(true).size() - parts.get(false).size();
    }
}
