package pl.konrad.swierszcz.day12.part2;

import java.util.*;
import java.util.regex.Pattern;

public class Solution {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d{1,}");
    private static final Pattern RED_GROUP_PATTERN = Pattern.compile("red");

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
        System.out.println(toSearch);
        var matcher = RED_GROUP_PATTERN.matcher(toSearch);
        int sum = 0;
        var exclusions = new ArrayList<Exclusion>();

        while (matcher.find()) {
            int exclusionBegin = findBracket(toSearch, matcher.start(), Direction.DECREMENT);
            int exclusionEnd = findBracket(toSearch, matcher.end(), Direction.INCREMENT);
            exclusions.add(new Exclusion(exclusionBegin, exclusionEnd));
        }

        //todo: check overlapping, merge, than sum of substrings from each (merged) exclusion

        return sum;
    }

    private static int findBracket(String toSearch, int startingPosition, Direction direction) {
        Map<Character, Integer> bracketOccurrences = new HashMap<>(Map.of('[', 0, ']', 0, '{', 0, '}', 0));

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
            return (brackets.get('[') >= brackets.get(']') && Objects.equals(brackets.get('{'), brackets.get('}'))) ||
                    (brackets.get('{') >= brackets.get('}') && Objects.equals(brackets.get('['), brackets.get(']')));
        }

        return (brackets.get('[') <= brackets.get(']') && Objects.equals(brackets.get('{'), brackets.get('}'))) ||
                (brackets.get('{') <= brackets.get('}') && Objects.equals(brackets.get('['), brackets.get(']')));
    }
}
