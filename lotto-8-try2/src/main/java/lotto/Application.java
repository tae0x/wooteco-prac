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
        printResult(result, lottoCount);
    }

    private static void printResult(Map<Rank, Integer> result, int lottoCount) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + result.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + result.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.get(Rank.FIRST) + "개");

        int totalPrize = 0;
        for (Rank rank : result.keySet()) {
            totalPrize += rank.getPrize() * result.get(rank);
        }

        double profitRate = (double) totalPrize / (lottoCount * 1000) * 100;
        System.out.printf("총 수익률은 %.1f%%입니다.", profitRate);
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
        System.out.println("\n" + lottoCount + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {

            List<Integer> immutableNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);

            List<Integer> mutableNumber = new ArrayList<>(immutableNumber);

            Collections.sort(mutableNumber);

            Lotto lotto = new Lotto(mutableNumber);

            lottos.add(lotto);

            System.out.println(lotto);
        }
        return lottos;
    }

}
