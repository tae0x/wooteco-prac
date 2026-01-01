package calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    public int calculate(String input) {
        if ((input == null) || input.isEmpty()) {
            return 0;
        }

        String delimiter = getDelimiter(input);

        String numbers = getNumberPart(input);

        return splitAndSum(numbers, delimiter);
    }

    private String getDelimiter(String input) {
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

    private String getNumberPart(String input) {
        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\\n");
            if (delimiterIndex != -1) {
                return input.substring(delimiterIndex + 2);
            }
        }
        return input;
    }

    private int splitAndSum(String numbers, String delimiter) {
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

    private int toInt(String part) {
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
