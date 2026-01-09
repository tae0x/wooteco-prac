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
                    throw new IllegalArgumentException("[ERROR] 양수를 입력해주세요");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 2. 자판기가 보유한 동전 게산
        Map<Coin, Integer> coinCount = new HashMap<>();
        for (Coin coin : Coin.values()) {
            coinCount.put(coin, 0);
        }

        int remaining = machineAmount;

        while (remaining > 0) {
            List<Integer> possibleCoins = new ArrayList<>();
            for (Coin coin : Coin.values()) {
                if (coin.getAmount() <= remaining) {
                    possibleCoins.add(coin.getAmount());
                }
            }

            int selectedAmount = Randoms.pickNumberInList(possibleCoins);

            for (Coin coin : Coin.values()) {
                if (coin.getAmount() == selectedAmount) {
                    coinCount.put(coin, coinCount.get(coin) + 1);
                    break;
                }
            }

            remaining -= selectedAmount;
        }

        // 3. 보유한 동전 현황 출력

        // 4. 상품 입력

        // 5. 투입 금액 입력

        // 6. 상품 구매(반복)

        // 7. 잔돈 계산

        // 8. 잔돈 현황 출력
    }
}
