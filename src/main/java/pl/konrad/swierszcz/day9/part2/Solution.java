package pl.konrad.swierszcz.day9.part2;

import pl.konrad.swierszcz.day9.Distance;
import pl.konrad.swierszcz.day9.DistanceEncoder;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static int longestDistanceBetweenLocations(List<String> inputs) {
        var distances = inputs.stream()
                .map(DistanceEncoder::encodeStringDistance)
                .collect(Collectors.toSet());

        var cities = distances.stream()
                .flatMap(distance -> distance.destinations().stream())
                .collect(Collectors.toSet());
        int pathLength = cities.size();

        return cities.stream()
                .map(city -> getShortestPathWithoutReturn(city, Map.of(city, 0), distances, cities, pathLength))
                .mapToInt(i -> i)
                .max().orElseThrow(NoSuchElementException::new);
    }

    private static int getShortestPathWithoutReturn(String actualPosition, Map<String, Integer> path, Set<Distance> distances, Set<String> places, int pathLength) {
        if (path.size() >= pathLength) {
            return path.values().stream()
                    .mapToInt(i -> i)
                    .sum();
        }

        var remainingPlaces = places.stream()
                .filter(place -> !place.equals(actualPosition))
                .collect(Collectors.toSet());

        var availableDistances = distances.stream()
                .filter(distance -> distance.destinations().contains(actualPosition))
                .filter(distance -> remainingPlaces.stream()
                        .anyMatch(place -> distance.destinations().contains(place))
                )
                .toList();

        return availableDistances.stream()
                .map(distance -> getShortestPathWithoutReturn(
                        distance.destinations().stream().filter(position -> !position.equals(actualPosition)).findAny().orElseThrow(NoSuchElementException::new),
                        connectPlaces(actualPosition, distance, path),
                        distances,
                        remainingPlaces,
                        pathLength
                ))
                .mapToInt(i -> i)
                .max().orElseThrow(RuntimeException::new);
    }

    private static Map<String, Integer> connectPlaces(String actualPosition, Distance usedConnection, Map<String, Integer> path) {
        String destination = usedConnection.destinations().stream()
                .filter(position -> !position.equals(actualPosition))
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return Stream.concat(
                        path.entrySet().stream(),
                        Stream.of(Map.entry(destination, usedConnection.distance()))
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
