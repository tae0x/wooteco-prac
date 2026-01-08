package calculator;

public class Application {
    public static void main(String[] args) {

        try {
            // 입력 받기
            String input = InputView.readInput();

            // 계산 하기
            int sum = Calculator.calculateSum(input);

            // 합계 출력
            OutputView.printResult(sum);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }
    }
}
