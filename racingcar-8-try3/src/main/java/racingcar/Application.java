package racingcar;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 1. 자동차 이름 입력
        List<String> carNames = InputView.readNameInput();

        // 2. 이동 횟수 입력
        int moveCount = InputView.readMoveCount();

        // 3. 경주 진행
        RacingGame game = new RacingGame(carNames);
        game.run(moveCount);
        List<Car> winners = game.findWinners();

        // 4. 우승자 출력
        OutputView.printWinners(winners);
    }
/*
    private static List<Car> runGame(List<String> carNames, int moveCount) {
        List<Car> cars = new ArrayList<>();

        for (String carName : carNames) {
            cars.add(new Car(carName));
        }

        OutputView.printRaceStart();

        for (int i = 0; i < moveCount; i++) {

            for (Car car : cars) {
                car.move();
                OutputView.printCarStatus(car);
            }
            OutputView.printNewLine();
        }
        return cars;
    }
 */
}
