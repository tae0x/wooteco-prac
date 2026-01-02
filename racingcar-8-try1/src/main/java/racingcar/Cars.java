package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void moveAll() {
        for (Car car : cars) {
            int randomValue = Randoms.pickNumberInRange(0, 9);
            car.move(randomValue);
        }
    }

    public String findWinnerName() {
        int maxPosition = findMaxPosition();
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


    private int findMaxPosition() {
        int maxPosition = 0;

        for (Car car : cars) {
            maxPosition = car.getMaxPostion(maxPosition);
        }
        return maxPosition;
    }

    public List<Car> getCars() {
        return cars;
    }
}
