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

        // 2. 자판기가 보유한 동전 랜덤 생성
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
        System.out.println("\n자판기가 보유한 동전");
        for (Coin coin : Coin.values()) {
            System.out.println(coin.getAmount() + "원 - " + coinCount.get(coin) + "개");
        }

        // 4. 상품 입력
        List<Product> products = new ArrayList<>();
        while (true) {
            System.out.println("\n상품명과 가격, 수량을 입력해 주세요.");
            String input = Console.readLine();

            try {
                String[] productTokens = input.split(";");
                for (String productToken : productTokens) {
                    String cleaned = productToken.substring(1, productToken.length() - 1);

                    String[] parts = cleaned.split(",");

                    String name = parts[0].trim();
                    int price = Integer.parseInt(parts[1].trim());
                    int quantity = Integer.parseInt(parts[2].trim());

                    if (price < 100) {
                        throw new IllegalArgumentException("[ERROR] 가격은 100원 이상이여야 합니다.");
                    }

                    if (price % 10 != 0) {
                        throw new IllegalArgumentException("[ERROR] 가격은 10원 단위여야 합니다");
                    }

                    products.add(new Product(name, price, quantity));
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 가격과 수량은 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("[ERROR] 올바른 형식으로 입력해주세요.");
            }
        }

        // 5. 투입 금액 입력

        // 6. 상품 구매(반복)

        // 7. 잔돈 계산

        // 8. 잔돈 현황 출력
    }
}
