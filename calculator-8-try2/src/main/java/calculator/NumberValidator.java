package calculator;

public class NumberValidator {
    public static int toInt(String part) {
        try {
            int num = Integer.parseInt(part); // try 블록 안으로 이동 (외부에 있을 시 문자가 들어오면 에러)
            if (num < 0) {
                throw new IllegalArgumentException("음수가 잘못 입력되었습니다.");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 것이 입력되었습니다.");
        }
    }
}
