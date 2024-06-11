package pl.konrad.swierszcz.day2;

import java.util.Arrays;

public class ElvesPackage {

    private final int shortSide;
    private final int mediumSide;
    private final int longSide;

    public ElvesPackage(String dimensions) {
        var dimensionsList = Arrays.stream(dimensions.split("x"))
                .map(Integer::valueOf)
                .sorted()
                .toList();

        shortSide = dimensionsList.get(0);
        mediumSide = dimensionsList.get(1);
        longSide = dimensionsList.get(2);
    }

    public int countRequiredPaper() {
        return 3 * shortSide * mediumSide + 2 * shortSide * longSide + 2 * mediumSide * longSide;
    }
}
