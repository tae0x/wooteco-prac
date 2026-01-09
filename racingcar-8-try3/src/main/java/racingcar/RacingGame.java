package racingcar;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {

    private final List<Car> cars;

    public RacingGame(List<String> carNames) {
        this.cars = new ArrayList<>();
        for (String carName : carNames) {
            cars.add(new Car(carName));
        }
    }

    public void run(int moveCount) {
        OutputView.printRaceStart();

        for (int i = 0; i < moveCount; i++) {

            for (Car car : cars) {
                car.move();
                OutputView.printCarStatus(car);
            }
            OutputView.printNewLine();
        }
    }

    public List<Car> findWinners() {
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
        return winningCars;
    }
}
