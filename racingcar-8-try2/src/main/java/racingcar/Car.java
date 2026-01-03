package racingcar;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    public void move(int number) {
        if (number >= 4) {
            this.position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isSamePosition(int otherPosition) {
        return this.position == otherPosition;
    }

    public int findMaxPosition(int maxPosition) {
        return Math.max(this.position, maxPosition);
    }
}
