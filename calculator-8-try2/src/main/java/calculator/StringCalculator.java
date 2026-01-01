package calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    public int calculate(String input) {
        if (input == null || input.isEmpty()) { // 빈 문자열 방어(안하면 에러)
            return 0;
        }
        String[] parts = parse(input);
        return sum(parts);
    }


    private String[] parse(String input) {
        String delimiter = ",|:";

        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\\n");
            if (delimiterIndex != -1) {
                String customDelimiter = input.substring(2, delimiterIndex);
                delimiter = Pattern.quote(customDelimiter); // 특수문자 구분자 방어
                input = input.substring(delimiterIndex + 2);
            }
        }

        return input.split(delimiter);
    }

    private int sum(String[] parts) {
        int sum = 0;
        for (String part : parts) {
            if (part.trim().isEmpty()) { // "1, ,2" 같은 경우 빈 문자열 무시
                continue;
            }
            sum += toInt(part);
        }
        return sum;
    }

    private int toInt(String part) {
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
