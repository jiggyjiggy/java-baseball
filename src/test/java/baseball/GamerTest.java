package baseball;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.*;

public class GamerTest {
    Gamer gamer = new Gamer();

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    @Test
    void 입력값이_세자리수의_정수값이_아니면_예외를_발생시킨다() {
        // Given
        String input = "1234";

        // When
        command(input);

        // Then
        assertThatThrownBy(() -> gamer.inputThreeDiffDigit())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력값이_replay_혹은_quit_의_숫자값이_아니면_예외를_발생시킨다() {
        // Given
        String input = "4";

        // When
        command(input);

        // Then
        assertThatThrownBy(() -> gamer.inputPlayAgainOrNot())
                .isInstanceOf(IllegalArgumentException.class);
    }


}
