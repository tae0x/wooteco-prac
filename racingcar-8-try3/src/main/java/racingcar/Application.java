package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 1. 자동차 이름 입력
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carNameInput = Console.readLine();

        String[] namesTokens = carNameInput.split(",");

        List<String> carNames = new ArrayList<>();

        for (String nameToken : namesTokens) {

            String trimmedNameToken = nameToken.trim();

            if (trimmedNameToken.isBlank()) {
                throw new IllegalArgumentException("이름은 공백이면 안됩니다.");
            }

            if (trimmedNameToken.length() > 5) {
                throw new IllegalArgumentException("이름은 5자 이하여야 합니다.");
            }

            carNames.add(trimmedNameToken);
        }

        // 2. 이동 횟수 입력

        // 3. 경주 진행

        // 4. 우승자 계산

    }
}
