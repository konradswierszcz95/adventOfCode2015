package pl.konrad.swierszcz.day2.part2;

import pl.konrad.swierszcz.day2.ElvesPackage;

import java.util.List;

public class Solution {
    public static int countRequiredRibbon(List<String> input) {
        return input.stream()
                .map(ElvesPackage::new)
                .map(ElvesPackage::countRibbon)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
