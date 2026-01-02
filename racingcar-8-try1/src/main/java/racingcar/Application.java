package racingcar;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        String[] names = InputView.readCarNames();
        int tryCount = InputView.readTryCount();

        Cars cars = new Cars(toCars(names));

        OutputView.printGameStart();
        playGame(tryCount, cars);

        String winners = cars.findWinnerName();
        OutputView.printWinner(winners);
    }

    private static List<Car> toCars(String[] names) {
        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            cars.add(new Car(name));
        }
        return cars;
    }

    private static void playGame(int tryCount, Cars cars) {
        for (int i = 0; i < tryCount; i++) {
            cars.moveAll();
            printStatus(cars);
            OutputView.printLine();
        }
    }

    private static void printStatus(Cars cars) {
        for (Car car : cars.getCars()) {
            OutputView.printCarStatus(car.getName(), car.getPosition());
        }
    }

}
