package calculator;

public class Application {
    public static void main(String[] args) {

        try {
            // 입력 받기
            String input = InputView.readInput();

            // 계산 하기
            int sum = calculateSum(input);

            // 합계 출력
            System.out.println("결과 : " + sum);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }
    }

    private static int calculateSum(String input) {

        if (input == null || input.isBlank()) {
            return 0;
        }

        String regex = extractDelimiter(input);

        String numbers = extractNumbers(input);

        String[] tokens = numbers.split(regex);

        return sum(tokens);
    }

    private static void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("양수를 입력하세요.");
        }
    }


    private static String extractDelimiter(String input) {
        if (input.startsWith("//") && input.contains("\\n")) {
            int lastIndex = input.indexOf("\\n");

            return input.substring(2, lastIndex);
        }
        return ",|:";
    }

    private static String extractNumbers(String input) {
        if (input.startsWith("//") && input.contains("\\n")) {
            int numbersStart = input.indexOf("\\n") + 2;
            return input.substring(numbersStart);
        }
        return input;
    }

    private static int sum(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            int number = parseInt(token);
            // 음수일시 예외
            validatePositive(number);

            sum += number;
        }
        return sum;
    }

    private static int parseInt(String token) {
        return Integer.parseInt(token);
    }
}
