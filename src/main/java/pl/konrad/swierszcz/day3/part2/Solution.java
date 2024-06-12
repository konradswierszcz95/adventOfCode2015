package pl.konrad.swierszcz.day3.part2;

import pl.konrad.swierszcz.day3.Home;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static int countHousesWithSantaVisit(String input) {
        var inputChars = input.toCharArray();
        Home lastSantaHome = new Home(0, 0);
        Home lastRoboHome = new Home(0, 0);

        Set<Home> visitedHomes = new HashSet<>();
        visitedHomes.add(lastSantaHome);

        for (int i = 0; i < inputChars.length; i++) {
            Home lastHome = i % 2 == 0 ? lastSantaHome : lastRoboHome;
            Home newHome = moveToAnotherHome(inputChars[i], lastHome);
            visitedHomes.add(newHome);

            lastSantaHome = i % 2 == 0 ? newHome : lastSantaHome;
            lastRoboHome = i % 2 == 1 ? newHome : lastRoboHome;
        }

        return visitedHomes.size();
    }

    private static Home moveToAnotherHome(char instruction, Home previousHome) {
        return switch (instruction) {
            case '<' -> new Home(previousHome.xPos() - 1, previousHome.yPos());
            case '>' -> new Home(previousHome.xPos() + 1, previousHome.yPos());
            case '^' -> new Home(previousHome.xPos(), previousHome.yPos() + 1);
            case 'v' -> new Home(previousHome.xPos(), previousHome.yPos() - 1);
            default -> throw new IllegalArgumentException("%s is not allowed".formatted(instruction));
        };
    }
}
