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

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < amount / 1000; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            List<Integer> sortedNumbers = new ArrayList<>(numbers);
            Collections.sort(sortedNumbers);

            Lotto lotto = new Lotto(sortedNumbers);
            lottos.add(lotto);
        }

        OutputView.printPurchaseResult(lottos);

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

        double totalEarnings = 0;
        for (Rank rank : result.keySet()) {
            Integer count = result.get(rank);

            totalEarnings += rank.getWinningMoney() * count;
        }
        double profitRate = (totalEarnings / amount) * 100;

        OutputView.printWinningStatistics(result, profitRate);
    }
}



