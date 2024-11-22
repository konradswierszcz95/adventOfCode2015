package pl.konrad.swierszcz.day9.part1;

import pl.konrad.swierszcz.day9.DistanceEncoder;

import java.util.List;

public class Solution {

    public static int shortestDistanceBetweenLocations(List<String> inputs) {
        var distances = inputs.stream()
                .map(DistanceEncoder::encodeStringDistance)
                .toList();

        var cities = distances.stream()
                .flatMap(distance -> distance.destinations().stream())
                .distinct()
                .toList();

        return 0;
    }
}
