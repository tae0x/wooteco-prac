package lotto;

import java.util.Map;

public class OutputView {

    public static void printResult(Map<Rank, Integer> result, int lottoCount) {
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

    public static void printPurchaseCount(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    public static void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public static void printError(String message) {
        System.out.println(message);
    }
}
