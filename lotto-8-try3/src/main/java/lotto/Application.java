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
        // 1. 구매 금액 입력

        int lottoCount = readInputMoney();

        // 2. 로또 발행

        List<Lotto> lottos = issueLotto(lottoCount);

        // 3. 당첨 번호 입력
        Lotto winningLotto = readInputWinningNumbers();

        // 4. 보너스 번호 입력
        int bonusNumber = readInputBonusNumber(winningLotto);

        // 5. 당첨 계산 및 통계
        Map<Rank, Integer> result = calculateWinner(lottos, winningLotto, bonusNumber);
        double profitRate = calculateProfit(result, lottoCount);

        // 6. 결과 출력
        printResult(result, profitRate);
    }

    private static double calculateProfit(Map<Rank, Integer> result, int lottoCount) {
        int totalPrize = 0;
        for (Rank rank : result.keySet()) {
            totalPrize += rank.getPrize() * result.get(rank);
        }
        return (double) totalPrize / (lottoCount * 1000) * 100;
    }

    private static Map<Rank, Integer> calculateWinner(List<Lotto> lottos, Lotto winningLotto, int bonusNumber) {
        Map<Rank, Integer> result = new HashMap<>();

        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = lotto.matchCount(winningLotto);
            boolean bonusMatch = lotto.contains(bonusNumber);

            Rank rank = Rank.valueOf(matchCount, bonusMatch);
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    private static void printResult(Map<Rank, Integer> result, double profitRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + result.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + result.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.get(Rank.FIRST) + "개");
        System.out.printf("총 수익률은 %.1f%%입니다.", profitRate);
    }

    private static int readInputBonusNumber(Lotto winningLotto) {
        int bonusNumber;
        while (true) {
            System.out.println("\n보너스 번호를 입력해 주세요.");
            String input = Console.readLine();

            try {
                bonusNumber = Integer.parseInt(input.trim());

                if (bonusNumber < 1 || bonusNumber > 45) {
                    throw new IllegalArgumentException("[ERROR] 1 ~ 45 사이의 숫자를 입력하세요.");
                }

                if (winningLotto.contains(bonusNumber)) {
                    throw new IllegalArgumentException("[ERROR] 당첨 번호와 중복될 수 없습니다.");
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력하세요.");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonusNumber;
    }

    private static Lotto readInputWinningNumbers() {
        Lotto winningLotto = null;

        while (true) {
            System.out.println("\n당첨 번호를 입력해 주세요.");
            String input = Console.readLine();
            try {
                String[] tokens = input.split(",");

                List<Integer> winningNumbers = new ArrayList<>();

                for (String token : tokens) {
                    int winningNumber = Integer.parseInt(token.trim());

                    if (winningNumber < 1 || winningNumber > 45) {
                        throw new IllegalArgumentException("[ERROR] 1 ~ 45 사이의 숫자를 입력하세요.");
                    }

                    winningNumbers.add(winningNumber);
                }

                winningLotto = new Lotto(winningNumbers);
                break;

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력하세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return winningLotto;
    }

    private static List<Lotto> issueLotto(int lottoCount) {
        System.out.println("\n" + lottoCount + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            List<Integer> mutableNumbers = new ArrayList<>(randomNumbers);
            Collections.sort(mutableNumbers);

            lottos.add(new Lotto(mutableNumbers));

            System.out.println(lottos.get(i));
        }
        return lottos;
    }

    private static int readInputMoney() {
        int lottoCount;
        while (true) {
            System.out.println("구입금액을 입력해주세요.");
            String input = Console.readLine();

            try {
                int money = Integer.parseInt(input);

                if (money <= 0 || money % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000 단위입니다.");
                }

                lottoCount = money / 1000;

                break;

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력하세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return lottoCount;
    }
}
