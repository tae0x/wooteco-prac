package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result;

    public LottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }
        this.result = result;
    }

    public double calculateProfitRate(int purchaseAmount) {
        double totalEarnings = 0;

        for (Rank rank : result.keySet()) {
            Integer count = result.get(rank);
            totalEarnings += rank.getWinningMoney() * count;
        }
        return (totalEarnings / purchaseAmount) * 100;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }
}
