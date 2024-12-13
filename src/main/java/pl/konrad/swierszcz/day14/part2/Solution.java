package pl.konrad.swierszcz.day14.part2;

import pl.konrad.swierszcz.day14.Reindeer;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    private Solution() {}

    public static int maxReindeerDistance(List<String> inputs, int raceLength) {
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

        var extraPoints = reindeers.stream()
                .map(r -> Map.entry(r.name(), 0))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return reindeers.stream()
                .mapToInt(r -> {
                    int rest = raceLength % r.period();
                    int fullPeriods = raceLength/ r.period();
                    int notFullPeriodDistance = Math.min(rest, r.flyTime());

                    return (fullPeriods * r.flyTime() + notFullPeriodDistance) * r.speed();
                })
                .max().orElseThrow();
    }

    private static String getLeader(List<Reindeer> reindeers, int time) {
        return reindeers.stream()
                .map(r -> {
                    int rest = time % r.period();
                    int fullPeriods = time / r.period();
                    int notFullPeriodDistance = Math.min(rest, r.flyTime());
                    Integer distance = (fullPeriods * r.flyTime() + notFullPeriodDistance) * r.speed();
                    return Map.entry(r.name(), distance);
                })
                .max(Comparator.comparing(Map.Entry::getValue)).orElseThrow().getKey();
    }
}
