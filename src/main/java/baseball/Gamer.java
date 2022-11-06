package baseball;

import baseball.enums.Announcement;
import baseball.validators.PlayAgainOrNotValidator;
import baseball.validators.ThreeDiffDigitValidator;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;


public class Gamer {
    public List<Integer> inputThreeDiffDigit() {
        Announcement.INPUT_NUMBER.printAnnouncement();
        String input = Console.readLine();

        new ThreeDiffDigitValidator().validate(input);

        return convertIntegerList(input);
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

    public String inputPlayAgainOrNot() {
        String input = Console.readLine();
        new PlayAgainOrNotValidator().validate(input);
        return input;
    }

}
