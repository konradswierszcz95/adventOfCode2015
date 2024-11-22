package pl.konrad.swierszcz.day10.part1;

public class Solution {

    public static String modifyStringByElvesRules(int iterations, String input) {
        String processingInput = input;

        for (int i = 0; i < iterations; i++) {
            processingInput = modifyCharacter(0, null, processingInput);
        }

        return processingInput;
    }

    private static String modifyCharacter(int duplications, Character lastCharacter, String remainingInput) {
        if (remainingInput.isEmpty()) {
            return "%s%s".formatted(duplications, lastCharacter);
        }

        Character actualCharacter = remainingInput.charAt(0);
        if (duplications == 0 || actualCharacter.equals(lastCharacter)) {
            return modifyCharacter(duplications + 1, actualCharacter, remainingInput.substring(1));
        }

        return "%s%s%s".formatted(
                duplications,
                lastCharacter,
                modifyCharacter(1, actualCharacter, remainingInput.substring(1))
        );
    }
}
