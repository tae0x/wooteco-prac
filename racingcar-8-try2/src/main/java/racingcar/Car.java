package racingcar;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MOVING_THRESHOLD = 4;

    private final String name;
    private int position = 0;

    public Car(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 " + MAX_NAME_LENGTH + "자 이하만 가능합니다.");
        }
        this.name = name;
    }

    public void move(int number) {
        if (number >= MOVING_THRESHOLD) {
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
