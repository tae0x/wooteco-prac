package lotto;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        List<Integer> uniqueNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (uniqueNumbers.contains(numbers.get(i))) {
                throw new IllegalArgumentException("[ERROR] 중복된 숫자는 입력할 수 없습니다.");
            }
            uniqueNumbers.add(numbers.get(i));
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
