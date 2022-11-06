package baseball;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameMachineTest {
    GameMachine gameMachine = new GameMachine();

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    @Test
    void 중복되는_숫자가_없는_길이가_3인_integer_list_생성한다() {
        // Given
        List<Integer> integerList = gameMachine.generateThreeDiffDigit();

        // When
        int size = integerList.size();
        long distinctIntegerListSize = integerList.stream().distinct().count();

        // Then
        assertThat(size).isEqualTo(3);
        assertThat(distinctIntegerListSize).isEqualTo(3);
    }

    @Test
    void 문자열의_문자_각각을_하나의_정수타입_원소로하는_integer_list_로_변환한다() {
        try {
            // Reflection
            Method convertIntegerListMethod = gameMachine.getClass().getDeclaredMethod("convertIntegerList", String.class);
            convertIntegerListMethod.setAccessible(true);

            // Given
            String input = "123";

            // When
            List<Integer> integerList = (List<Integer>) convertIntegerListMethod.invoke(gameMachine, input);

            // Then
            List<Integer> result = List.of(1, 2, 3);
            assertThat(integerList).isEqualTo(result);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void gamer에게_입력받은_문자열을_integer_list_로_반환한다() {
        // Given
        Gamer gamer = new Gamer();
        String input = "123";
        command(input);

        // When
        List<Integer> threeDiffDigit = gameMachine.askInputThreeDiffDigit(gamer);

        // Then
        List<Integer> integerList = List.of(1, 2, 3);
        assertThat(threeDiffDigit).isEqualTo(integerList);
    }

    @Test
    void strike면_game의_numberOfStrike의_수를_증가시키고_ball이면_game의_numberOfball의_수를_증가시킨다() {
        try {
            // Reflection
            Method countBallTypeMethod = gameMachine.getClass().getDeclaredMethod("countBallType", String.class);
            countBallTypeMethod.setAccessible(true);

            // Given
            Game game = new Game();

            int digitOfGame = 1;
            int index = 0;

            int digitOfGamer = 1;
            int jndex = 2;

            // When
            countBallTypeMethod.invoke(digitOfGame, digitOfGamer, index, jndex, game);

            // Then
            int numberOfStrike = game.getNumberOfStrike();
            int resultOfStrike = 0;

            int numberOfBall = game.getNumberOfBall();
            int resultOfBall = 1;

            assertThat(numberOfStrike).isEqualTo(resultOfStrike);
            assertThat(numberOfBall).isEqualTo(resultOfBall);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 입력값의_각_자리수에_대해_ball_인지_strike_인지_아무판정이_아닌지에_판단한다() {
        try {
            // Reflection
            Method judgeBallMethod = gameMachine.getClass().getDeclaredMethod("judgeBall", String.class);
            judgeBallMethod.setAccessible(true);

            // Given
            Game game = new Game();
            game.setThreeDiffDigit(List.of(1, 3, 5));

            List<Integer> threeDiffDigitOfGamer = List.of(1, 2, 3);

            // When
            judgeBallMethod.invoke(threeDiffDigitOfGamer, game);

            // Then
            int numberOfStrike = game.getNumberOfStrike();
            int resultOfStrike = 1;

            int numberOfBall = game.getNumberOfBall();
            int resultOfBall = 1;

            assertThat(numberOfStrike).isEqualTo(resultOfStrike);
            assertThat(numberOfBall).isEqualTo(resultOfBall);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void game의_strike개수와_ball개수를_ball_count_문자열로_변환한다() {
        try {
            // Reflection
            Method convertBallCountStrMethod = gameMachine.getClass().getDeclaredMethod("convertBallCountStr", String.class);
            convertBallCountStrMethod.setAccessible(true);

            // Given
            Game game = new Game();
            game.setNumberOfStrike(2);
            game.setNumberOfBall(1);

            // When
            String ballCountStr = (String) convertBallCountStrMethod.invoke(gameMachine, game);

            // Then
            String result = "1볼 2스트라이크";
            assertThat(ballCountStr).isEqualTo(result);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void gamer_의_입력값으로_ball_인지_strike인지_판별하고_game의_ball_count_를_업데이트한다() {
        // Given
        Game game = new Game();
        game.setThreeDiffDigit(List.of(1, 3, 5));

        List<Integer> threeDiffDigitOfGamer = List.of(1, 2, 3);

        // When
        gameMachine.updateBallCount(threeDiffDigitOfGamer, game);
        String ballCount = game.getBallCount();

        // Then
        String result = "1볼 1스트라이크";
        assertThat(ballCount).isEqualTo(result);
    }


    @Nested
    class gamer에게_입력받은_값이 {

        @Test
        void replay_값이면_true를_반환하고() {
            // Given
            Gamer gamer = new Gamer();
            String input = "1";
            command(input);

            // When
            boolean playFlag = gameMachine.askReplay(gamer);

            // Then
            boolean result = true;
            assertThat(playFlag).isEqualTo(result);
        }

        @Test
        void quit_값이면_false를_반환한다() {
            // Given
            Gamer gamer = new Gamer();
            String input = "2";
            command(input);

            // When
            boolean playFlag = gameMachine.askReplay(gamer);

            // Then
            boolean result = false;
            assertThat(playFlag).isEqualTo(result);
        }
    }
}


