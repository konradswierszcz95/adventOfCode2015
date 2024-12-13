package pl.konrad.swierszcz.day14.part1;

import pl.konrad.swierszcz.day14.Reindeer;

import java.util.List;

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

        return reindeers.stream()
                .mapToInt(r -> {
                    int rest = raceLength % r.period();
                    int fullPeriods = raceLength/ r.period();
                    int notFullPeriodDistance = Math.min(rest, r.flyTime());

                    return (fullPeriods * r.flyTime() + notFullPeriodDistance) * r.speed();
                })
                .max().orElseThrow();
    }
}
