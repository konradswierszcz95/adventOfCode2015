package pl.konrad.swierszcz.day9;

import java.util.Set;

public class DistanceEncoder {
    public static Distance encodeStringDistance(String input) {
        var inputParts = input.split(" ");

        return new Distance(Set.of(inputParts[0], inputParts[2]), Integer.parseInt(inputParts[4]));
    }
}
