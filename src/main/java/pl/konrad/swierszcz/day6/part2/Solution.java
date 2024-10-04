package pl.konrad.swierszcz.day6.part2;

import pl.konrad.swierszcz.day6.Instruction;
import pl.konrad.swierszcz.day6.LightsGrid;

import java.util.List;

class Solution {

    private Solution(){}

    public static int countOnLightsAfterInstructions(List<String> input) {
        var lightsGrid = new LightsGrid(1000, 1000);

        input.forEach(instruction -> executeInstruction(instruction, lightsGrid));

        return lightsGrid.countLights();
    }

    private static void executeInstruction(String instruction, LightsGrid lightsGrid) {
        var parsedInstruction = new Instruction(instruction);

        switch (parsedInstruction.getInstructionType()) {
            case TOGGLE -> lightsGrid.doubleIncreaseLights(parsedInstruction.getStartX(), parsedInstruction.getStartY(), parsedInstruction.getEndX(), parsedInstruction.getEndY());
            case TURN_ON -> lightsGrid.increaseLights(parsedInstruction.getStartX(), parsedInstruction.getStartY(), parsedInstruction.getEndX(), parsedInstruction.getEndY());
            case TURN_OFF-> lightsGrid.decreaseLights(parsedInstruction.getStartX(), parsedInstruction.getStartY(), parsedInstruction.getEndX(), parsedInstruction.getEndY());
        }
    }
}
