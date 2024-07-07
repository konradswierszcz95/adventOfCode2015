package pl.konrad.swierszcz.day4.part2;

import org.apache.commons.codec.digest.DigestUtils;

public class Solution {
    public static int getLowestHashProducingSixZeros(String input) {
        int lastTested = 1;
        while (!hasFiveLeadingZeros(DigestUtils.md5Hex(input + lastTested))) {
            lastTested++;
        }

        return lastTested;
    }

    private static boolean hasFiveLeadingZeros(String md5Hash) {
        return md5Hash.startsWith("0".repeat(6));
    }
}
