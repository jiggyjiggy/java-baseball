package baseball;

import baseball.enums.Announcement;
import baseball.enums.BallCount;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Gamer {
    protected void play(GameMachine gameMachine, Game game) {
        List<Integer> integerList = gameMachine.generateThreeDiffDigit();
        game.setThreeDiffDigit(integerList);

        while(!Objects.equals(game.getBallCount(), BallCount.THREE_STRIKES.getKorean())) {
            game.initializeBallCount();

            List<Integer> threeDiffDigitOfGamer = inputThreeDiffDigit();

            gameMachine.updateBallCount(threeDiffDigitOfGamer, game);
            System.out.println(game.getBallCount());
        }
        Announcement.MATCH_THREE_NUMBER.printAnnouncement();
    }

    private List<Integer> inputThreeDiffDigit() {
        Announcement.INPUT_NUMBER.printAnnouncement();
        String input = Console.readLine();

        validationInput(input);
//        new GamerInputValidator().validationInput(input);

        return convertIntegerList(input);
    }

    private void validationInput(String input) {
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

    private List<Integer> convertIntegerList(String str) {
        List<Integer> integerList = new ArrayList<>();

        String[] strArr = str.split("");
        for (String element:
                strArr) {
            Integer convertedElement = Integer.parseInt(element);
            integerList.add(convertedElement);
        }
        return integerList;
    }

}
