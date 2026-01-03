package racingcar;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String[] readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");

        String inputCarNames = Console.readLine().trim();
        String[] carNames = inputCarNames.trim().split(",");

        return carNames;
    }


    public static int readTryCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputTryCount = Console.readLine();
        int tryCount;
        try {
            tryCount = Integer.parseInt(inputTryCount);
            if (tryCount <= 0) {
                throw new IllegalArgumentException("시도 횟수는 1 이상만 가능합니다.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력 가능합니다.");
        }
        return tryCount;
    }

}
