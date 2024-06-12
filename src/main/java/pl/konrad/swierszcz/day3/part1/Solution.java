package pl.konrad.swierszcz.day3.part1;

import pl.konrad.swierszcz.day3.Home;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static int countHousesWithSantaVisit(String input) {
        var inputChars = input.toCharArray();
        Home lastHome = new Home(0, 0);

        Set<Home> visitedHomes = new HashSet<>();
        visitedHomes.add(lastHome);

        for (char inputChar : inputChars) {
            lastHome = switch (inputChar) {
                case '<' -> new Home(lastHome.xPos() - 1, lastHome.yPos());
                case '>' -> new Home(lastHome.xPos() + 1, lastHome.yPos());
                case '^' -> new Home(lastHome.xPos(), lastHome.yPos() + 1);
                case 'v' -> new Home(lastHome.xPos(), lastHome.yPos() - 1);
                default -> throw new IllegalArgumentException("%s is not allowed".formatted(inputChar));
            };

            visitedHomes.add(lastHome);
        }

        return visitedHomes.size();
    }
}
