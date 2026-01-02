package racingcar;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String[] readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputNames = Console.readLine();
        String[] names = inputNames.split(",");

        validateNames(names);

        return names;
    }

    private static void validateNames(String[] names) {
        for (String name : names) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
            }
        }
    }

    public static int readTryCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputCount = Console.readLine();

        int tryCount = parseToInt(inputCount);

        validateTryCount(tryCount);

        return tryCount;
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    private static void validateTryCount(int tryCount) {
        if (tryCount <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }
}
