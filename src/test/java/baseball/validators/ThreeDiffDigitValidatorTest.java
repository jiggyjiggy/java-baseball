package baseball.validators;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ThreeDiffDigitValidatorTest {
    ThreeDiffDigitValidator threeDiffDigitValidator = new ThreeDiffDigitValidator();

    @Test
    void 입력값의_길이가_3보다_크면_예외를_발생시킨다() {
        try {
            // Reflection
            Method checkLengthThreeMethod = threeDiffDigitValidator.getClass().getDeclaredMethod("checkLengthThree", String.class);
            checkLengthThreeMethod.setAccessible(true);

            // Given
            String input = "1234";

            //  When & Then
            assertThatThrownBy(() -> checkLengthThreeMethod.invoke(threeDiffDigitValidator, input))
                    .getCause().isInstanceOf(IllegalArgumentException.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 입력값이_숫자타입으로_형변환_할_수_없는_문자열이면_예외를_발생시킨다() {
        try {
            // Reflection
            Method checkIntegerMethod = threeDiffDigitValidator.getClass().getDeclaredMethod("checkInteger", String.class);
            checkIntegerMethod.setAccessible(true);

            // Given
            String input = "$123";

            //  When & Then
            assertThatThrownBy(() -> checkIntegerMethod.invoke(threeDiffDigitValidator, input))
                    .getCause().isInstanceOf(IllegalArgumentException.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 입력값의_각_자리수_중_중복값이_있다면_예외를_발생시킨다() {
        try {
            // Reflection
            Method checkDiffDigitMethod = threeDiffDigitValidator.getClass().getDeclaredMethod("checkDiffDigit", String.class);
            checkDiffDigitMethod.setAccessible(true);

            // Given
            String input = "122";

            //  When & Then
            assertThatThrownBy(() -> checkDiffDigitMethod.invoke(threeDiffDigitValidator, input))
                    .getCause().isInstanceOf(IllegalArgumentException.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 입력값이_제약조건에서_벗어나면_예외를_발생시킨다() {
        // Given
        String input = "12343";

        // When & Then
        assertThatThrownBy(() -> threeDiffDigitValidator.validate(input))
                        .isInstanceOf(IllegalArgumentException.class);
    }

}
