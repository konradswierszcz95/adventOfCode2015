package pl.konrad.swierszcz.day7.part1;

class SignalOperationDecoder {
    public static SignalOperation decodeOperation(String instruction) {
        var operationType = findOperationType(instruction);
        return switch (operationType) {
            case DIRECT -> mapDirectOperation(instruction);
            case AND, OR -> mapAndOrOrOperation(instruction, operationType);
            case NOT -> mapNotOperation(instruction);
            case LEFT_SHIFT, RIGHT_SHIFT -> mapShiftOperation(instruction, operationType);
        };
    }

    private static OperationType findOperationType(String instruction) {
        if (instruction.contains("AND")) {
            return OperationType.AND;
        }

        if (instruction.contains("OR")) {
            return OperationType.OR;
        }

        if (instruction.contains("NOT")) {
            return OperationType.NOT;
        }

        if (instruction.contains("RSHIFT")) {
            return OperationType.RIGHT_SHIFT;
        }

        if (instruction.contains("LSHIFT")) {
            return OperationType.LEFT_SHIFT;
        }

        return OperationType.DIRECT;
    }

    private static SignalOperation mapDirectOperation(String instruction) {
        var split = instruction.split(" -> ");

        return new SignalOperation(
                OperationType.DIRECT,
                split[1],
                mapInputValue(split[0]),
                new InputSignal(SignalTypes.EMPTY, null, null),
                null
        );
    }

    private static InputSignal mapInputValue(String input) {
        try {
            return new InputSignal(SignalTypes.NUMERICAL, Integer.valueOf(input), null);
        } catch (NumberFormatException ex) {
            return new InputSignal(SignalTypes.NAMED, null, input);
        }
    }

    private static SignalOperation mapAndOrOrOperation(String instruction, OperationType operationType) {
        var split = instruction.split(" ");

        return new SignalOperation(
                operationType,
                split[4],
                mapInputValue(split[0]),
                mapInputValue(split[2]),
                null
        );
    }

    private static SignalOperation mapShiftOperation(String instruction, OperationType operationType) {
        var split = instruction.split(" ");

        return new SignalOperation(
                operationType,
                split[4],
                mapInputValue(split[0]),
                new InputSignal(SignalTypes.EMPTY, null, null),
                Integer.valueOf(split[2])
        );
    }

    private static SignalOperation mapNotOperation(String instruction) {
        var split = instruction.split(" ");

        return new SignalOperation(
                OperationType.NOT,
                split[3],
                mapInputValue(split[0]),
                new InputSignal(SignalTypes.EMPTY, null, null),
                null
        );
    }
}
