package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {

        try {
            // 입력 받기
            System.out.println("덧셈할 문자열을 입력해 주세요.");
            String input = Console.readLine();

            int sum = 0;

            // null 이나 공백인 경우 0 반환
            if (input == null || input.isBlank()) {
                System.out.println("결과 : " + sum);
                return;
            }

            String regex = ",|:";

            if (input.startsWith("//") && input.contains("\\n")) {
                int lastIndex = input.indexOf("\\n");

                String customRegex = input.substring(2, lastIndex);

                regex = customRegex;

                input = input.substring(lastIndex + 2);
            }

            // 구분자로 분리
            String[] tokens = input.split(regex);

            for (String token : tokens) {
                int number = Integer.parseInt(token);

                // 음수일시 예외
                if (number < 0) {
                    throw new IllegalArgumentException("양수를 입력하세요.");
                }
                // 합계 계산
                sum += number;
            }

            // 합계 출력
            System.out.println("결과 : " + sum);

            // 문자일시 예외
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }
    }
}
