package calculator;

public class NumberValidator {
    private static final String ERROR_NOT_NUMBER = "숫자가 아닌 값이 포함되어 있습니다: ";
    private static final String ERROR_NEGATIVE = "음수는 입력할 수 없습니다.";

    public static int toInt(String input) {
        try {
            int number = Integer.parseInt(input);
            validatePositive(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER + input);
        }
    }

    public static void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }
}
