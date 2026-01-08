package lotto;

import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.lotto = new Lotto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto userLotto) {
        int matchCount = lotto.countMatch(userLotto.getNumbers());

        boolean matchBonus = lotto.contains(bonusNumber);

        Rank rank = Rank.valueOf(matchCount, matchBonus);

        return rank;
    }


}
