package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {

        // 1. 구입 금액 입력
        int lottoCount = InputView.readPurchaseAmount();

        // 2. 로또 발행
        List<Lotto> lottos = issueLottos(lottoCount);

        // 3. 당첨 번호 입력
        Lotto winningLotto = InputView.readWinningNumber();

        // 4. 보너스 번호 입력
        int bonusNumber = InputView.readBonusNumber(winningLotto);

        // 5. 당첨 확인 및 통계
        Map<Rank, Integer> result = checkWinning(lottos, winningLotto, bonusNumber);

        // 6. 결과 출력
        OutputView.printResult(result, lottoCount);
    }


    private static Map<Rank, Integer> checkWinning(List<Lotto> lottos, Lotto winningLotto, int bonusNumber) {
        Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        // 각 로또 마다 반복
        for (Lotto lotto : lottos) {
            // 당첨 번호와 일치 개수 확인
            int matchCount = lotto.countMatch(winningLotto);
            // 보너스 번호 포함 여부 확인
            boolean bonusMatch = lotto.contains(bonusNumber);
            // 등수 판정
            Rank rank = Rank.valueOf(matchCount, bonusMatch);
            // 등수별 개수 카운트
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }


    private static List<Lotto> issueLottos(int lottoCount) {
        OutputView.printPurchaseCount(lottoCount);

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {

            List<Integer> immutableNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);

            List<Integer> mutableNumber = new ArrayList<>(immutableNumber);

            Collections.sort(mutableNumber);

            Lotto lotto = new Lotto(mutableNumber);

            lottos.add(lotto);

            OutputView.printLotto(lotto);
        }
        return lottos;
    }

}
