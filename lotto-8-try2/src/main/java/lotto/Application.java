package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        // 1. 구입 금액 입력
        int lottoCount = 0;

        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            try {
                String readInput = Console.readLine();
                int purchaseAmount = Integer.parseInt(readInput);

                if (purchaseAmount <= 0 || purchaseAmount % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입금액은 1,000원 단위여야 합니다.");
                }

                lottoCount = purchaseAmount / 1000;
                break;

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 구입 금액은 숫자만 가능합니다.");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 2. 로또 발행
        System.out.println(lottoCount + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {

            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);

            Collections.sort(numbers);

            Lotto lotto = new Lotto(numbers);

            lottos.add(lotto);

            System.out.println(lotto);
        }

        // 3. 당첨 번호 입력

        // 4. 보너스 번호 입력

        // 5. 당첨 확인 및 통계

        // 6. 결과 출력
    }
}
