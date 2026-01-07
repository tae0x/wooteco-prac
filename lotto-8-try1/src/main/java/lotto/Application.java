package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        System.out.println("구입 금액을 입력해 주세요.");
        String input = Console.readLine();

        int amount = Integer.parseInt(input);
        int count = amount / 1000;
        System.out.println("\n" + count + "개를 구매했습니다.");

        List<List<Integer>> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1,45, count);

            Collections.sort(numbers);

            System.out.println(numbers);
        }
    }
}
