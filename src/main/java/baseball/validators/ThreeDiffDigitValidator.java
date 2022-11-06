package baseball.validators;

public class ThreeDiffDigitValidator {
    public void validate(String input) {
        checkLengthThree(input);
        checkInteger(input);
    }

    private void checkLengthThree(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException();
        }
    }

    private void checkInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

}