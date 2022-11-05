package baseball.validation;

public class GamerInputValidator {

    public void validationInput(String input) {
        if (!isLengthThree(input) || !isInteger(input)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isLengthThree(String input) {
        return input.length() == 3;
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
