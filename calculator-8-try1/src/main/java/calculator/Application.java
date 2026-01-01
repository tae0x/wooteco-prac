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

        String delimiter = ",|:";
        String numbers = input;

        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\\n");
            if (delimiterIndex != -1) {
                String customDelimiter = input.substring(2, delimiterIndex);
                // split에서 특수문자가 오작동하지 않도록 처리
                delimiter = Pattern.quote(customDelimiter);
                // 숫자 부분 추출 (\n 다음부터 끝까지)
                numbers = input.substring(delimiterIndex + 2);
            }
        }

        String[] strings = numbers.split(delimiter);
        int sum = 0;

        for (String string : strings) {

            try {
                int num = Integer.parseInt(string);

                if (num < 0) {
                    throw new IllegalArgumentException("음수는 입력할 수 없습니다");
                }
                sum += num;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: ");
            }
        }
        return sum;
    }
}
