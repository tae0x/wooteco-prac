package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {

        int amount = InputView.inputPurchaseAmount();

        List<Lotto> lottos = LottoMachine.buyLottos(amount);

        OutputView.printPurchaseResult(lottos);

        List<Integer> correctNumbers = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber(correctNumbers);

        WinningLotto winningLotto = new WinningLotto(correctNumbers, bonusNumber);

        Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
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



