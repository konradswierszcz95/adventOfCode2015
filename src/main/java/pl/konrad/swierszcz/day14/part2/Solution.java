package pl.konrad.swierszcz.day14.part2;

import pl.konrad.swierszcz.day14.Reindeer;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    private Solution() {}

    public static long maxReindeerDistance(List<String> inputs, int raceLength) {
        var reindeers = inputs.stream()
                .map(input -> input.split(" "))
                .map(split -> new Reindeer(
                        split[0],
                        Integer.parseInt(split[6]),
                        Integer.parseInt(split[3]),
                        Integer.parseInt(split[13]),
                        Integer.parseInt(split[6]) + Integer.parseInt(split[13])
                ))
                .toList();

        var leadershipPoints = IntStream.range(1, raceLength + 1)
                .mapToObj(i -> getLeaders(reindeers, i))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return leadershipPoints.values()
                .stream().max(Long::compareTo)
                .orElse(0L);
    }

    private static List<String> getLeaders(List<Reindeer> reindeers, int time) {
        var distanceGroups = reindeers.stream()
                .map(r -> {
                    int rest = time % r.period();
                    int fullPeriods = time / r.period();
                    int notFullPeriodDistance = Math.min(rest, r.flyTime());
                    Integer distance = (fullPeriods * r.flyTime() + notFullPeriodDistance) * r.speed();
                    return Map.entry(r.name(), distance);
                })
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

        int maxDistance = distanceGroups.keySet().stream()
                .max(Integer::compareTo)
                .orElse(0);

        return distanceGroups.getOrDefault(maxDistance, Collections.emptyList());
    }
}
