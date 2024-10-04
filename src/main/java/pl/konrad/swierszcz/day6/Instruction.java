package pl.konrad.swierszcz.day6;

public class Instruction {

    private final Type instructionType;
    private final int startX;
    private final int endX;
    private final int startY;
    private final int endY;

    public Instruction(String instruction) {
        var splitInstruction = instruction.split(" ");

        var isTurnType = splitInstruction[0].equals("turn");

        if (!isTurnType) {
            instructionType = Type.TOGGLE;
        } else {
            instructionType = splitInstruction[1].equals("on") ? Type.TURN_ON : Type.TURN_OFF;
        }

        var startPosition = isTurnType ? splitInstruction[2] : splitInstruction[1];
        var startPositionSplit = startPosition.split(",");
        var endPosition = splitInstruction[splitInstruction.length - 1];
        var endPositionSplit = endPosition.split(",");

        startX = Integer.parseInt(startPositionSplit[0]);
        startY = Integer.parseInt(startPositionSplit[1]);
        endX = Integer.parseInt(endPositionSplit[0]);
        endY = Integer.parseInt(endPositionSplit[1]);
    }

    public enum Type {
        TURN_ON,
        TURN_OFF,
        TOGGLE
    }

    public Type getInstructionType() {
        return instructionType;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }
}
