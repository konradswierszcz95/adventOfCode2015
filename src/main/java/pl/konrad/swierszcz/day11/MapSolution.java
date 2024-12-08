package pl.konrad.swierszcz.day11;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSolution {
    private static final List<Character> FORBIDDEN_LETTERS = List.of('i', 'o', 'l');

    private MapSolution() {
    }

    public static String initGettingNextCorrectPassword(String input) {
        var password = getMapOfCharactersPositions(input);

        return getNextCorrectPassword(password, password).entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(e -> Character.toString(e.getValue()))
                .collect(Collectors.joining());
    }

    private static Map<Integer, Character> getNextCorrectPassword(Map<Integer, Character> originalPassword, Map<Integer, Character> actualPassword) {
        var forbiddenLettersPositions = findForbiddenLetters(actualPassword);
        if (!forbiddenLettersPositions.isEmpty()) {
            return getNextCorrectPassword(originalPassword, removeForbiddenLetters(actualPassword, forbiddenLettersPositions));
        }

        var pairEnds = getPairsEnds(actualPassword);
        if (pairEnds.size() < 2) {
            return getNextCorrectPassword(originalPassword, addMissingPairs(actualPassword, pairEnds));
        }

        if (!containsSequence(actualPassword)) {
            return getNextCorrectPassword(originalPassword, insertSequence(actualPassword));
        }

        if (!isBigger(actualPassword, originalPassword)) {
            return getNextCorrectPassword(originalPassword, incrementPassword(actualPassword, null));
        }

        return actualPassword;
    }

    private static Map<Integer, Character> getMapOfCharactersPositions(String password) {
        var chars = password.toCharArray();
        Map<Integer, Character> result = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            result.put(i, chars[i]);
        }

        return result;
    }

    private static boolean isBigger(Map<Integer, Character> biggerPass, Map<Integer, Character> smallerPass) {
        if (smallerPass.size() != biggerPass.size()) {
            throw new RuntimeException("Compared passwords must have same length");
        }

        for (int i = 0; i < smallerPass.size() - 1; i++) {
            if (biggerPass.get(i) < smallerPass.get(i)) {
                return false;
            } else if (biggerPass.get(i) > smallerPass.get(i)) {
                return true;
            }
        }

        return biggerPass.get(smallerPass.size() - 1) > smallerPass.get(smallerPass.size() - 1);
    }

    private static List<Integer> findForbiddenLetters(Map<Integer, Character> password) {
        return password.entrySet().stream()
                .filter(e -> FORBIDDEN_LETTERS.contains(e.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }

    private static Map<Integer, Character> removeForbiddenLetters(Map<Integer, Character> actualPassword, List<Integer> forbiddenLetters) {
        if (forbiddenLetters.isEmpty()) {
            return actualPassword;
        }

        int firstForbiddenLetterPosition = forbiddenLetters.stream().mapToInt(i -> i).min().orElseThrow(() -> new RuntimeException("Position of first forbidden letter not found"));

        var partDirectIncremented = incrementPassword(actualPassword, firstForbiddenLetterPosition);

        var newPass = new HashMap<>(partDirectIncremented);

        for (int i = firstForbiddenLetterPosition + 1; i < actualPassword.size(); i++) {
            newPass.put(i, 'a');
        }

        return newPass;
    }

    private static Map<Integer, Character> addMissingPairs(Map<Integer, Character> actualPassword, Map<Character, Integer> pairEnds) {
        if (pairEnds.size() >= 2) {
            return actualPassword;
        }
        int passSize = actualPassword.size();

        var newPassword = new HashMap<Integer, Character>();

        if (
                pairEnds.isEmpty() ||
                        pairEnds.entrySet().stream().findAny().orElseThrow(() -> new RuntimeException("Pair expected but not found")).getValue() > passSize - 2
        ) {
            var directCopy = actualPassword.entrySet().stream()
                    .filter(e -> e.getKey() < passSize - 3)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            newPassword.putAll(directCopy);

            char secondPairCharacter = actualPassword.get(passSize - 4) >= actualPassword.get(passSize - 3) ? actualPassword.get(passSize - 4) : incrementLetter(actualPassword.get(passSize - 4), true);
            if (actualPassword.get(passSize - 4) == 'z') {
                incrementPassword(actualPassword, passSize - 5);
            }
            char firstPairCharacter = secondPairCharacter == 'a' ? 'b' : 'a';

            newPassword.put(passSize - 4, secondPairCharacter);
            newPassword.put(passSize - 3, secondPairCharacter);
            newPassword.put(passSize - 2, firstPairCharacter);
            newPassword.put(passSize - 1, firstPairCharacter);

            return newPassword;
        }

        var pair = pairEnds.entrySet().stream().findAny().orElseThrow(() -> new RuntimeException("Pair expected but not found"));
        var directCopy = actualPassword.entrySet().stream()
                .filter(e -> e.getKey() < passSize - 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        newPassword.putAll(directCopy);

        char smallerPairCharacter = pair.getKey() != 'a' ? 'a' : 'b';
        newPassword.put(passSize - 2, smallerPairCharacter);
        newPassword.put(passSize - 1, smallerPairCharacter);

        return newPassword;

    }

    private static Map<Integer, Character> incrementPassword(Map<Integer, Character> password, Integer firstPosition) {
        HashMap<Integer, Character> newPass = new HashMap<>(password);
        int incrementationBegin = firstPosition != null ? firstPosition : password.size() - 1;
        for (int i = incrementationBegin; i >= 0; i--) {
            char incrementedLetter = incrementLetter(newPass.get(i), true);
            newPass.put(i, incrementedLetter);

            if (incrementedLetter != 'a') {
                return newPass;
            }
        }

        return newPass;
    }

    private static Character incrementLetter(Character letter, boolean omitForbiddenLetters) {
        if (letter == 'z') {
            return 'a';
        }

        if (omitForbiddenLetters) {
            return FORBIDDEN_LETTERS.contains((char) (letter + 1)) ? (char) (letter + 2) : (char) (letter + 1);
        }

        return (char) (letter + 1);
    }

    private static Map<Character, Integer> getPairsEnds(Map<Integer, Character> password) {
        var result = new HashMap<Character, Integer>();
        for (int i = password.size() - 1; i > 0; i--) {
            if (password.get(i) == password.get(i - 1)) {
                result.put(password.get(i), i);
            }
        }

        return result;
    }

    private static boolean containsSequence(Map<Integer, Character> password) {
        for (int i = 0; i < password.size() - 2; i++) {
            if (password.get(i + 1) == incrementLetter(password.get(i), false) && password.get(i + 2) == incrementLetter(password.get(i + 1), false)) {
                return true;
            }
        }

        return false;
    }

    private static Map<Integer, Character> insertSequence(Map<Integer, Character> password) {
        var pairs = getPairsEnds(password);
        Integer smallerPairEnd = pairs.keySet().stream()
                .mapToInt(i -> i)
                .max().orElse(-1);
        var newPass = new HashMap<Integer, Character>();
        boolean shouldPasswordBeIncremented;

        if (!pairs.isEmpty() && smallerPairEnd < password.size() - 3) {
            var directCopy = password.entrySet().stream()
                    .filter(e -> e.getKey() < password.size() - 2)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            newPass.putAll(directCopy);
            newPass.put(password.size() - 2, incrementLetter(newPass.get(password.size() - 2), false));
            newPass.put(password.size() - 1, incrementLetter(newPass.get(password.size() - 2), false));

            shouldPasswordBeIncremented = newPass.get(password.size() - 2) == 'a' || newPass.get(password.size() - 1) == 'a';
            if (shouldPasswordBeIncremented) {
                int incrementationPosition = newPass.get(password.size() - 2) == 'a' ? password.size() - 3 : password.size() - 2;
                newPass.putAll(incrementPassword(newPass, incrementationPosition));
            }
        }

        var directCopy = password.entrySet().stream()
                .filter(e -> e.getKey() < password.size() - 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        newPass.putAll(directCopy);
        newPass.put(password.size() - 3, incrementLetter(password.get(password.size() - 3), false));
        newPass.put(password.size() - 2, incrementLetter(password.get(password.size() - 2), false));
        newPass.put(password.size() - 1, incrementLetter(password.get(password.size() - 2), false));

        shouldPasswordBeIncremented = newPass.get(password.size() - 3) == 'a' || newPass.get(password.size() - 2) == 'a' || newPass.get(password.size() - 1) == 'a';
        if (shouldPasswordBeIncremented) {
            int incrementationPosition = newPass.get(password.size() - 3) == 'a' ? password.size() - 4 :
                    newPass.get(password.size() - 2) == 'a' ? password.size() - 3 : password.size() - 2;
            newPass.putAll(incrementPassword(newPass, incrementationPosition));
        }

        return newPass;
    }
}
