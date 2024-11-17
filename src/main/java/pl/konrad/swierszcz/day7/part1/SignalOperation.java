package pl.konrad.swierszcz.day7.part1;

public class SignalOperation {
    private final OperationType operationType;
    private final String outputName;
    private InputSignal firstInput;
    private InputSignal secondInput;
    private final Integer shiftValue;

    public SignalOperation(
            OperationType operationType,
            String outputName,
            InputSignal firstInput,
            InputSignal secondInput,
            Integer shiftValue
    ) {
        this.operationType = operationType;
        this.outputName = outputName;
        this.firstInput = firstInput;
        this.secondInput = secondInput;
        this.shiftValue = shiftValue;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public String getOutputName() {
        return outputName;
    }

    public Integer getShiftValue() {
        return shiftValue;
    }

    public void setFirstInputValue(Integer value) {
        firstInput = new InputSignal(SignalTypes.NUMERICAL, value, firstInput.name());
    }

    public String getFirstInputName() {
        return firstInput.name();
    }

    public Integer getFirstInputValue() {
        return firstInput.value();
    }

    public void setSecondInputValue(Integer value) {
        secondInput = new InputSignal(SignalTypes.NUMERICAL, value, secondInput.name());
    }

    public String getSecondInputName() {
        return secondInput.name();
    }

    public Integer getSecondInputValue() {
        return secondInput.value();
    }

    public SignalTypes getSecondInputType() {
        return secondInput.signalType();
    }

    public boolean areInputsNumerical() {
        var isFirstInputNumerical = firstInput.signalType().equals(SignalTypes.NUMERICAL) || firstInput.signalType().equals(SignalTypes.EMPTY);
        var isSecondInputNumerical = secondInput.signalType().equals(SignalTypes.NUMERICAL) || secondInput.signalType().equals(SignalTypes.EMPTY);

        return isFirstInputNumerical && isSecondInputNumerical;
    }
}
