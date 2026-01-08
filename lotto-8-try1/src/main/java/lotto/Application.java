package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {

        int amount = InputView.inputPurchaseAmount();

        int lottoCount = amount / 1000;
        System.out.println("\n" + lottoCount + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            List<Integer> sortedNumbers = new ArrayList<>(numbers);
            Collections.sort(sortedNumbers);

            Lotto lotto = new Lotto(sortedNumbers);

            System.out.println(lotto.getNumbers());

            lottos.add(lotto);
        }

        List<Integer> correctNumbers = InputView.inputWinningNumbers();

        int bonusNumber = InputView.inputBonusNumber(correctNumbers);

        Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatch(correctNumbers);

            boolean matchBonus = lotto.contains(bonusNumber);

            Rank rank = Rank.valueOf(matchCount, matchBonus);
            result.put(rank, result.get(rank) + 1);
        }

        System.out.println("\n당첨 통계");
        System.out.println("---");

        System.out.println("3개 일치 (5,000원) - " + result.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + result.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.get(Rank.FIRST) + "개");

        double totalEarnings = 0;
        for (Rank rank : result.keySet()) {
            Integer count = result.get(rank);

            totalEarnings += rank.getWinningMoney() * count;
        }
        double profitRate = (totalEarnings / amount) * 100;

        System.out.printf("총 수익률은 %.1f%%입니다.\n", profitRate);
    }
}
