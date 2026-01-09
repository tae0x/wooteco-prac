package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // 1. 자판기 보유 금액 입력
        int machineAmount;
        while (true) {
            System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
            String input = Console.readLine();
            try {
                machineAmount = Integer.parseInt(input);

                if (machineAmount < 0) {
                    throw new IllegalArgumentException("[ERROR] 금액은 양수여야 합니다.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 금액은 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 2. 보유 금액으로 동전 랜덤 생성
        Map<Coin, Integer> coinCount = new HashMap<>();
        int remaining = machineAmount;

        for (Coin coin : Coin.values()) {
            int coinAmount = coin.getAmount();
            int maxCount = remaining / coinAmount;

            List<Integer> candidates = new ArrayList<>();
            for (int i = 0; i <= maxCount; i++) {
                candidates.add(i);
            }
            int count = Randoms.pickNumberInList(candidates);

            coinCount.put(coin, count);

            remaining = remaining - (count * coinAmount);
        }

        // 3. 생성된 동전 출력
        System.out.println("\n자판기가 보유한 동전");
        for (Coin coin : Coin.values()) {
            int count = coinCount.get(coin);
            System.out.println(coin.getAmount() + "원 - " + count + "개");
        }

        // 4. 상품 정보 입력 (파싱 필요)

        // 5. 투입 금액 입력

        // 6. 반복: 상품 구매
        //    - 투입 금액 출력
        //    - 구매 상품명 입력
        //    - 구매 처리 (재고 감소, 금액 감소)
        //    - 종료 조건: 잔액 < 최저가 OR 재고 없음

        // 7. 잔돈 반환 (보유 동전으로 최대한)

        // 8. 반환된 동전 출력
    }
}
