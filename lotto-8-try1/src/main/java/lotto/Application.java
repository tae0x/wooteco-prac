package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {

        int amount = 0;
        while (true) {
            try {
                System.out.println("구입 금액을 입력해 주세요.");
                String inputMoney = Console.readLine();
                amount = Integer.parseInt(inputMoney);

                if (amount % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 구입 금액은 숫자만 가능합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        int lottoCount = amount / 1000;
        System.out.println("\n" + lottoCount + "개를 구매했습니다.");

        List<List<Integer>> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            List<Integer> sortedNumbers = new ArrayList<>(numbers);
            Collections.sort(sortedNumbers);

            System.out.println(sortedNumbers);
            lottos.add(sortedNumbers);
        }

        System.out.println("\n당첨 번호를 입력해 주세요.");
        String inputCorrectNumber = Console.readLine();
        String[] split = inputCorrectNumber.split(",");
        List<Integer> correctNumbers = new ArrayList<>();

        for (String s : split) {
            correctNumbers.add(Integer.parseInt(s.trim()));
        }

        System.out.println("\n보너스 번호를 입력해주세요.");
        String inputBonusNumber = Console.readLine();
        int bonusNumber = Integer.parseInt(inputBonusNumber);

        Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (List<Integer> lotto : lottos) {
            int matchCount = 0;
            for (Integer number : lotto) {
                if (correctNumbers.contains(number)) {
                    matchCount++;
                }
            }

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
