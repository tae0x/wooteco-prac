package calculator;

public class NumberValidator {
    private static final String ERROR_NEGATIVE = "음수는 잘못 입력되었습니다.";
    private static final String ERROR_NOT_NUMBER = "숫자가 아닌 것이 입력되었습니다.";

    public static int toInt(String part) {
        try {
            int num = Integer.parseInt(part); // try 블록 안으로 이동 (외부에 있을 시 문자가 들어오면 에러)
            if (num < 0) {
                throw new IllegalArgumentException(ERROR_NEGATIVE);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }
}
