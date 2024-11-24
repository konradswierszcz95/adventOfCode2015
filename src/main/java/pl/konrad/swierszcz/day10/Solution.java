package pl.konrad.swierszcz.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static Integer getSizeOfInputModifiedByElvesRules(int iterations, String input) {
        var sequence = Arrays.stream(input.split(""))
                .map(Integer::valueOf)
                .toList();

        for (int i = 0; i < iterations; i++) {
            sequence = modifySequence(sequence);
        }

        return sequence.size();
    }

    private static List<Integer> modifySequence(List<Integer> sequence) {
        var modifiedSequence = new ArrayList<Integer>();

        int duplications = 0;
        int lastCharacter = sequence.getFirst();

        for (Integer integer : sequence) {
            if (lastCharacter == integer) {
                duplications++;
            } else {
                modifiedSequence.add(duplications);
                modifiedSequence.add(lastCharacter);
                duplications = 1;
                lastCharacter = integer;
            }
        }

        modifiedSequence.add(duplications);
        modifiedSequence.add(lastCharacter);

        return modifiedSequence;
    }
}
