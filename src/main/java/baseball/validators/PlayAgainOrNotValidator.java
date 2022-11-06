package baseball.validators;

import baseball.enums.Menu;

public class PlayAgainOrNotValidator {

    public void validate(String input) {
        checkMenuInteger(input);
    }

    private void checkMenuInteger(String input) {
        if (!(input.equals(Menu.REPLAY.getMenuNumber()) || input.equals(Menu.QUIT.getMenuNumber()))) {
            throw new IllegalArgumentException();
        }
    }
}
