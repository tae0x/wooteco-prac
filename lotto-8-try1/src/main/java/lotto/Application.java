package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        System.out.println("구입 금액을 입력해 주세요.");
        String inputMoney = Console.readLine();

        int count = Integer.parseInt(inputMoney) / 1000;
        System.out.println("\n" + count + "개를 구매했습니다.");

        List<List<Integer>> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1,45, 6);
            Collections.sort(numbers);

            System.out.println(numbers);
            lottos.add(numbers);
        }

        System.out.println("\n당첨 번호를 입력해 주세요.");
        String inputCorrectNumber = Console.readLine();
        String[] split = inputCorrectNumber.split(",");
        List<Integer> correctNumbers = new ArrayList<>();

        for (String s : split) {
            correctNumbers.add(Integer.parseInt(s.trim()));
        }

        System.out.println("보너스 번호를 입력해주세요.");
        String inputBonusNumber = Console.readLine();
        int bonusNumber = Integer.parseInt(inputBonusNumber);

    }
}
