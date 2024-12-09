package pl.konrad.swierszcz.day12.part2;

import java.util.*;
import java.util.regex.Pattern;

class Solution {
    private Solution() {}

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d{1,}");
    private static final Pattern RED_GROUP_PATTERN = Pattern.compile(":\"red");

    public static int getSumOfNumbers(List<String> input) {
        return input.stream()
                .mapToInt(s -> getNumberSum(s) - getRedGroupSum(s))
                .sum();
    }

    private static int getNumberSum(String toSearch) {
        var matcher = NUMBER_PATTERN.matcher(toSearch);
        int sum = 0;

        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group());
        }

        return sum;
    }

    private static int getRedGroupSum(String toSearch) {
        var matcher = RED_GROUP_PATTERN.matcher(toSearch);
        var exclusions = new ArrayList<Exclusion>();

        while (matcher.find()) {
            int exclusionBegin = findBracket(toSearch, matcher.start(), Direction.DECREMENT);
            int exclusionEnd = findBracket(toSearch, matcher.end(), Direction.INCREMENT);
            exclusions.add(new Exclusion(exclusionBegin, exclusionEnd, exclusionEnd - exclusionBegin));
        }

        return mergeOverlappingExclusions(exclusions.stream().distinct().toList()).stream()
                .mapToInt(e -> getNumberSum(toSearch.substring(e.getBegin(), e.getEnd() +1)))
                .sum();
    }

    private static int findBracket(String toSearch, int startingPosition, Direction direction) {
        Map<Character, Integer> bracketOccurrences = new HashMap<>(Map.of('{', 0, '}', 0));

        int actualPosition = startingPosition;
        while (getEndingStatement(bracketOccurrences, direction)) {
            actualPosition = direction.equals(Direction.INCREMENT) ? actualPosition + 1 : actualPosition - 1;

            char actualChar = toSearch.charAt(actualPosition);
            if (bracketOccurrences.containsKey(actualChar)) {
                bracketOccurrences.put(actualChar, bracketOccurrences.get(actualChar) + 1);
            }
        }

        return actualPosition;
    }

    private static boolean getEndingStatement(Map<Character, Integer> brackets, Direction direction) {
        if (direction.equals(Direction.INCREMENT)) {
            return brackets.get('{') >= brackets.get('}');
        }

        return brackets.get('{') <= brackets.get('}');
    }

    private static Set<Exclusion> mergeOverlappingExclusions(List<Exclusion> exclusions) {
        var mergedExclusions = new HashSet<Exclusion>();
        for (Exclusion ex: exclusions) {
            var overLappingExclusions = exclusions.stream()
                    .filter(ex::isOverlapping)
                    .toList();

            mergedExclusions.add(mergeExclusions(overLappingExclusions));
        }

        return mergedExclusions;
    }

    private static Exclusion mergeExclusions(List<Exclusion> exclusions) {
        var sortedExclusions = exclusions.stream()
                .sorted(Comparator.comparingInt(Exclusion::getSize).reversed())
                .toList();
        var exclusion = sortedExclusions.getFirst();
        if (sortedExclusions.size() == 1) {
            return exclusion;
        }

        for (int i = 1; i < sortedExclusions.size(); i++) {
            exclusion = exclusion.merge(sortedExclusions.get(i));
        }

        return exclusion;
    }
}
