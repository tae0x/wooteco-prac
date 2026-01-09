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

    @Override
    public String toString() {

        StringBuilder distance = new StringBuilder();
        for (int i = 0; i < position; i++) {
            distance.append("-");
        }

        return name + " : " + distance.toString();
    }
}
