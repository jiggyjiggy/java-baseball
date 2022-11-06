package baseball.validators;

import baseball.validators.PlayAgainOrNotValidator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayAgainOrNotValidatorTest {
    PlayAgainOrNotValidator playAgainOrNotValidator = new PlayAgainOrNotValidator();

    @Test
    void 입력값이_1_혹은_2가_아니면_예외발생() {
        try {
            // Reflection
            Method checkMenuNumberMethod = playAgainOrNotValidator.getClass().getDeclaredMethod("checkMenuNumber", String.class);
            checkMenuNumberMethod.setAccessible(true);

            // Given
            String input = "3";

            // When & Then
            assertThatThrownBy(() -> checkMenuNumberMethod.invoke(playAgainOrNotValidator, input))
                    .getCause().isInstanceOf(IllegalArgumentException.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 입력값이_제약조건에서_벗어나면_예외발생() {
        // Given
        String input = "3";

        // When & Then
        assertThatThrownBy(() -> playAgainOrNotValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
