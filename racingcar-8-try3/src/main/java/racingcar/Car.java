package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {

    private final String name;
    private int position;

    public Car(String name) {
        this.name = name;
        this.position = 0;
    }

    public void move() {
        int randomValue = Randoms.pickNumberInRange(0, 9);
        if (randomValue >= 4) {
            position++;
        }
    }

    public String getName() {
        return name;
    }

    public int compareMaxPosition(int maxPosition) {
        return Math.max(position, maxPosition);
    }

    public boolean isSameMaxPosition(int maxPosition) {
        return this.position == maxPosition;
    }

    @Override
    public String toString() {

        return name + " : " + "-".repeat(position);
    }
}
