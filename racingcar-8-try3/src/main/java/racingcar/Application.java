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

        System.out.println("시도할 횟수는 몇 회인가요?");
        String tryInput = Console.readLine();
        int moveCount;

        try {
            moveCount = Integer.parseInt(tryInput);

            if (moveCount <= 0) {
                throw new IllegalArgumentException("이동 횟수는 양수만 가능합니다.");
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력하세요.");
        }

        // 3. 경주 진행
        List<Car> cars = new ArrayList<>();

        for (String carName : carNames) {
            cars.add(new Car(carName));
        }

        for (int i = 0; i < moveCount; i++) {

            for (Car car : cars) {
                car.move();
                System.out.println(car);
            }
            System.out.println();
        }

        // 4. 우승자 계산

    }
}
