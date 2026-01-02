package racingcar;

public class Car {
    private final String name;
    private int position;

    public Car(String name) {
        validateNames(name);
        this.name = name;
        this.position = 0;
    }

    private void validateNames(String name) {
        if (name == null || name.length() > 5) {
            throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
        }
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

    public boolean isSamePosition(int position) {
        return this.position == position;
    }

    public int getMaxPostion(int otherPositon) {
        return Math.max(this.position, otherPositon);
    }
}
