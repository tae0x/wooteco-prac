package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 1. 구매 금액 입력

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

        // 2. 로또 발행

        System.out.println("\n" + lottoCount + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            List<Integer> mutableNumbers = new ArrayList<>(randomNumbers);
            Collections.sort(mutableNumbers);

            lottos.add(new Lotto(mutableNumbers));

            System.out.println(lottos.get(i));
        }

        // 3. 당첨 번호 입력
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

        // 4. 보너스 번호 입력
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

        // 5. 당첨 계산 및 통계

        // 6. 결과 출력
    }
}
