package pl.konrad.swierszcz.day1.part2;

public class Solution {
    public static long getBasementEnterPosition(String input) {
        int floor = 0;
        int position = 0;
        var inputChars = input.toCharArray();

        while (floor >= 0) {
            if (inputChars[position] == '(') {
                floor++;
            } else {
                floor--;
            }

            position++;
        }

        return position;
    }
}
