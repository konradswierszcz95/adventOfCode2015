package pl.konrad.swierszcz.day6;

import java.util.Arrays;

public class LightsGrid {
    private int[][] arrayGrid;

    public LightsGrid(int xLength, int yLength) {
        this.arrayGrid = new int[xLength][yLength];

        for(int x = 0; x < xLength; x++) {
            Arrays.fill(arrayGrid[x], 0);
        }
    }

    public void turnOnLights(int xStart, int yStart, int xEnd, int yEnd) {
       for (int i = xStart; i <= xEnd; i++) {
           for (int j = yStart; j <= yEnd; j++) {
               arrayGrid[i][j] = 1;
           }
       }
    }

    public void turnOffLights(int xStart, int yStart, int xEnd, int yEnd) {
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                arrayGrid[i][j] = 0;
            }
        }
    }

    public void toggleLights(int xStart, int yStart, int xEnd, int yEnd) {
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                arrayGrid[i][j] = arrayGrid[i][j] == 0 ? 1 : 0;
            }
        }
    }

    public void increaseLights(int xStart, int yStart, int xEnd, int yEnd) {
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                arrayGrid[i][j]++;
            }
        }
    }

    public void decreaseLights(int xStart, int yStart, int xEnd, int yEnd) {
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                arrayGrid[i][j] = arrayGrid[i][j] > 1 ? arrayGrid[i][j] - 1 : 0;
            }
        }
    }

    public void doubleIncreaseLights(int xStart, int yStart, int xEnd, int yEnd) {
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                arrayGrid[i][j] = arrayGrid[i][j] + 2;
            }
        }
    }

    public int countLights() {
        return Arrays.asList(arrayGrid)
                .parallelStream()
                .flatMapToInt(Arrays::stream)
                .sum();
    }
}
