package calculator;

public class Application {
    public static void main(String[] args) {

        run();
    }

    private static void run() {
        try {
            execute();

        } catch (NumberFormatException e) {
            handleInvalidInput();
        }
    }

    private static void handleInvalidInput() {
        throw new IllegalArgumentException("숫자를 입력하세요.");
    }

    private static void execute() {
        // 입력 받기
        String input = InputView.readInput();

        // 계산 하기
        int sum = Calculator.calculateSum(input);

        // 합계 출력
        OutputView.printResult(sum);
    }
}
