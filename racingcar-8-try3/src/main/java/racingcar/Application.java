package racingcar;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 1. 자동차 이름 입력
        List<String> carNames = InputView.readNameInput();

        // 2. 이동 횟수 입력
        int moveCount = InputView.readMoveCount();

        // 3. 경주 진행
        List<Car> cars = runCars(carNames, moveCount);

        // 4. 우승자 계산
        printWinner(cars);
    }

    private static void printWinner(List<Car> cars) {
        int maxPosition = 0;

        for (Car car : cars) {
            maxPosition = car.compareMaxPosition(maxPosition);
        }

        List<Car> winningCars = new ArrayList<>();

        for (Car car : cars) {
            if (car.isSameMaxPosition(maxPosition)) {
                winningCars.add(car);
            }
        }
        
        OutputView.printWinners(winningCars);
    }

    private static List<Car> runCars(List<String> carNames, int moveCount) {
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


}
