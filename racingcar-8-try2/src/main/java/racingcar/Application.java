package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Car> cars = initCars();

        int tryCount = InputView.readTryCount();

        runRace(tryCount, cars);

        printWinners(cars);
    }


    private static void printWinners(List<Car> cars) {
        int maxPosition = 0;

        for (Car car : cars) {
            maxPosition = car.findMaxPosition(maxPosition);
        }

        StringBuilder winners = new StringBuilder();

        for (Car car : cars) {
            if (car.isSamePosition(maxPosition)) {
                if (winners.length() > 0) {
                    winners.append(", ");
                }
                winners.append(car.getName());
            }
            OutputView.printWinner(winners.toString());
        }
    }

    private static void runRace(int tryCount, List<Car> cars) {
        OutputView.printGameStart();

        for (int i = 0; i < tryCount; i++) {
            for (Car car : cars) {
                int randomValue = Randoms.pickNumberInRange(0, 9);
                car.move(randomValue);

                OutputView.printCarStatus(car.getName(), car.getPosition());
            }
            System.out.println();
        }
    }

    private static List<Car> initCars() {
        String[] carNames = InputView.readCarNames();

        List<Car> cars = new ArrayList<>();
        for (String carName : carNames) {
            cars.add(new Car(carName));
        }
        return cars;
    }
}
