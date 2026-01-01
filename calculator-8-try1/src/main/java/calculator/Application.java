package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = calculate(input);
        System.out.println("결과 : " + result);
    }

    private static int calculate(String input) {
        if ((input == null) || input.isEmpty()) {
            return 0;
        }

        String delimiter = getDelimiter(input);

        String numbers = getNumberPart(input);

        return splitAndSum(numbers, delimiter);
    }

    private static String getDelimiter(String input) {
        String delimiter = ",|:";

        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\\n");
            if (delimiterIndex != -1) {
                String customDelimiter = input.substring(2, delimiterIndex);
                delimiter = Pattern.quote(customDelimiter);
            }
        }

        return delimiter;
    }

    private static String getNumberPart(String input) {
        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\\n");
            if (delimiterIndex != -1) {
                return input.substring(delimiterIndex + 2);
            }
        }
        return input;
    }

    private static int splitAndSum(String numbers, String delimiter) {
        String[] parts = numbers.split(delimiter);
        int sum = 0;

        for (String part : parts) {
            if (part.trim().isBlank()) {
                continue;
            }
            sum += toInt(part);
        }
        return sum;
    }

    private static int toInt(String part) {
        try {
            int number = Integer.parseInt(part);
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다");
        }
    }
}
