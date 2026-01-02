package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        String[] names = InputView.readCarNames();
        int tryCount = InputView.readTryCount();

        List<Car> cars = toCars(names);

        OutputView.printGameStart();
        playGame(tryCount, cars);

        String winners = findWinner(cars);
        OutputView.printWinner(winners);
    }

    private static List<Car> toCars(String[] names) {
        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            cars.add(new Car(name));
        }
        return cars;
    }

    private static void playGame(int tryCount, List<Car> cars) {
        for (int i = 0; i < tryCount; i++) {
            playOneRound(cars);
            OutputView.printLine();
        }
    }

    private static void playOneRound(List<Car> cars) {
        for (Car car : cars) {
            int randomValue = Randoms.pickNumberInRange(0, 9);
            car.move(randomValue);
            OutputView.printCarStatus(car.getName(), car.getPosition());
        }
    }

    private static String findWinner(List<Car> cars) {
        int maxPosition = findMaxPosition(cars);

        StringBuilder winners = new StringBuilder();

        for (Car car : cars) {
            if (car.isSamePosition(maxPosition)) {

                if (winners.length() > 0) {
                    winners.append(", ");
                }
                winners.append(car.getName());
            }
        }
        return winners.toString();
    }

    private static int findMaxPosition(List<Car> cars) {
        int maxPosition = 0;

        for (Car car : cars) {
            maxPosition = car.getMaxPostion(maxPosition);
        }
        return maxPosition;
    }

}
