package pl.konrad.swierszcz.day7.part1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static int getASignalValue(List<String> input) {
        var decodedOperations = input.stream()
                .map(SignalOperationDecoder::decodeOperation)
                .toList();

        Map<String, Integer> signalValues = new HashMap<>(input.size());
        insertDirectValues(decodedOperations, signalValues);
        insertModifiedValues(decodedOperations, signalValues);

        return signalValues.getOrDefault("a", 0);
    }

    private static void insertDirectValues(List<SignalOperation> operations, Map<String, Integer> operationMap) {
        operations.stream()
                .filter(operation -> operation.getOperationType().equals(OperationType.DIRECT))
                .filter(SignalOperation::areInputsNumerical)
                .forEach(operation -> operationMap.put(operation.getOutputName(), operation.getFirstInputValue()));
    }

    private static void insertModifiedValues(List<SignalOperation> operations, Map<String, Integer> operationMap) {
        while (operationMap.size() <= operations.size()) {
            var ableToFill = operations.stream()
                    .filter(operation -> Solution.isSignalAbleToFill(operation, operationMap))
                    .toList();

            ableToFill.forEach(
                    operation -> operationMap.put(operation.getOutputName(), getSignalValue(operation, operationMap))
            );
        }
    }

    private static boolean isSignalAbleToFill(SignalOperation operation, Map<String, Integer> operationMap) {
        if (operationMap.containsKey(operation.getOutputName())) {
            return false;
        }

        boolean isFirstInputKnown = operation.getFirstInputValue() != null || operationMap.containsKey(operation.getFirstInputName());
        boolean isSecondInputKnown = operation.getSecondInputType().equals(SignalTypes.EMPTY) || operation.getSecondInputValue() != null ||
                operationMap.containsKey(operation.getSecondInputName());

        return isFirstInputKnown && isSecondInputKnown;
    }

    private static int getSignalValue(SignalOperation operation, Map<String, Integer> actualMapContent) {
        return switch (operation.getOperationType()) {
            case DIRECT -> actualMapContent.get(operation.getFirstInputName());
            case AND -> getAndValue(actualMapContent.get(operation.getFirstInputName()), actualMapContent.get(operation.getSecondInputName()));
            case OR -> getOrValue(actualMapContent.get(operation.getFirstInputName()), actualMapContent.get(operation.getSecondInputName()));
            case NOT -> getNotValue(actualMapContent.get(operation.getFirstInputName()));
            case LEFT_SHIFT -> getLeftShiftedValue(actualMapContent.get(operation.getFirstInputName()), operation.getShiftValue());
            case RIGHT_SHIFT -> getRightShiftedValue(actualMapContent.get(operation.getFirstInputName()), operation.getShiftValue());
        };
    }

    private static int getNotValue(int input) {
        return ~input;
    }

    private static int getOrValue(int firstInput, int secondInput) {
        return firstInput | secondInput;
    }

    private static int getAndValue(int firstInput, int secondInput) {
        return firstInput & secondInput;
    }

    private static int getLeftShiftedValue(int input, int shitValue) {
        return input << shitValue;
    }

    private static int getRightShiftedValue(int input, int shitValue) {
        return input >> shitValue;
    }
}
