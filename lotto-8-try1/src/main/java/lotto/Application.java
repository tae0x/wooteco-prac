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

        System.out.println("\n당첨 통계");
        System.out.println("---");

        int match3 = 0;
        int match4 = 0;
        int match5 = 0;
        int match5Bonus = 0;
        int match6 = 0;

        for (List<Integer> lotto : lottos) {
            int matchCount = 0;
            for (Integer number : lotto) {
                if (correctNumbers.contains(number)) {
                    matchCount++;
                }
            }

            boolean matchBonus = lotto.contains(bonusNumber);

            if (matchCount == 6) {
                match6++;
            } else if (matchCount == 5) {
                if (matchBonus) {
                    match5Bonus++;
                } else {
                    match5++;
                }
            } else if (matchCount == 4) {
                match4++;
            } else if (matchCount == 3) {
                match3++;
            }
        }

        System.out.println("3개 일치 (5,000원) - " + match3 + "개");
        System.out.println("4개 일치 (50,000원) - " + match4 + "개");
        System.out.println("5개 일치 (1,500,000원) - " + match5 + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + match5Bonus + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + match6 + "개");

    }
}
