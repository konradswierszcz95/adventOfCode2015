package pl.konrad.swierszcz.day2.part1;

import pl.konrad.swierszcz.day2.ElvesPackage;

import java.util.List;

public class Solution {
    public static int countRequiredPaper(List<String> input) {
        return input.stream()
                .map(ElvesPackage::new)
                .map(ElvesPackage::countRequiredPaper)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
