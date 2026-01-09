package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // 1. 자판기 보유 금액 입력 (양수 검증, 재입력)
        int machineAmount = 0;
        while (true) {
            System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
            String input = Console.readLine();
            try {
                machineAmount = Integer.parseInt(input);
                if (machineAmount < 0) {
                    throw new IllegalArgumentException("[ERROR] 금액은 양수여야 합니다.");
                }
                break;  // 정상 입력 시 while 탈출
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 금액은 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 2. 보유 금액으로 동전 랜덤 생성
        // 핵심: 금액을 동전으로 "하나씩" 변환 (예: 450원 → 100원 4개 + 50원 1개)
        Map<Coin, Integer> coinCount = new HashMap<>();

        // 모든 동전 0개로 초기화
        for (Coin coin : Coin.values()) {
            coinCount.put(coin, 0);
        }

        int remaining = machineAmount;  // 남은 금액

        // 남은 금액이 0이 될 때까지 동전 하나씩 생성
        while (remaining > 0) {
            // 현재 남은 금액으로 만들 수 있는 동전 액면가들만 추출
            List<Integer> possibleCoins = new ArrayList<>();
            for (Coin coin : Coin.values()) {
                if (coin.getAmount() <= remaining) {  // 남은 금액 이하의 동전만
                    possibleCoins.add(coin.getAmount());
                }
            }

            // 가능한 동전들 중 랜덤으로 하나 선택
            int selectedAmount = Randoms.pickNumberInList(possibleCoins);

            // 선택된 동전 개수 1 증가
            for (Coin coin : Coin.values()) {
                if (coin.getAmount() == selectedAmount) {
                    coinCount.put(coin, coinCount.get(coin) + 1);
                    break;
                }
            }

            remaining -= selectedAmount;  // 남은 금액 차감
        }

        // 3. 생성된 동전 출력
        System.out.println();
        System.out.println("자판기가 보유한 동전");
        for (Coin coin : Coin.values()) {
            System.out.println(coin.getAmount() + "원 - " + coinCount.get(coin) + "개");
        }

        // 4. 상품 정보 입력 (파싱: [이름,가격,수량];[이름,가격,수량])
        List<Product> products = new ArrayList<>();
        while (true) {
            System.out.println();
            System.out.println("상품명과 가격, 수량을 입력해 주세요.");
            String input = Console.readLine();

            try {
                String[] productTokens = input.split(";");  // 세미콜론으로 상품 분리

                for (String productToken : productTokens) {
                    // 대괄호 제거: "[콜라,1500,20]" → "콜라,1500,20"
                    String cleaned = productToken.substring(1, productToken.length() - 1);

                    // 쉼표로 분리
                    String[] parts = cleaned.split(",");
                    String name = parts[0].trim();
                    int price = Integer.parseInt(parts[1].trim());
                    int quantity = Integer.parseInt(parts[2]);

                    // 가격 검증
                    if (price < 100) {
                        throw new IllegalArgumentException("[ERROR] 가격은 100원 이상이어야 합니다.");
                    }
                    if (price % 10 != 0) {
                        throw new IllegalArgumentException("[ERROR] 가격은 10원 단위여야 합니다.");
                    }

                    products.add(new Product(name, price, quantity));
                }
                break;  // 모든 상품 정상 처리 시 while 탈출

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 가격과 수량은 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("[ERROR] 올바른 형식으로 입력해 주세요.");
            }
        }

        // 5. 투입 금액 입력 (양수 검증)
        int userMoney;
        while (true) {
            System.out.println();
            System.out.println("투입 금액을 입력해 주세요.");
            String input = Console.readLine();

            try {
                userMoney = Integer.parseInt(input);
                if (userMoney < 0) {
                    throw new IllegalArgumentException("[ERROR] 금액은 양수여야 합니다.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 금액은 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 6. 상품 구매 반복 (종료: 잔액 < 최저가 OR 재고 없음)
        while (true) {
            System.out.println();
            System.out.println("투입 금액: " + userMoney + "원");

            // 재고 있는 상품 중 최저 가격 찾기
            int minPrice = Integer.MAX_VALUE;
            for (Product product : products) {
                if (product.getQuantity() > 0 && product.getPrice() < minPrice) {
                    minPrice = product.getPrice();
                }
            }

            // 종료 조건: 투입 금액 < 최저 가격
            if (userMoney < minPrice) {
                break;
            }

            System.out.println("구매할 상품명을 입력해 주세요.");
            String productName = Console.readLine();

            // 상품 찾기
            Product foundProduct = null;
            for (Product product : products) {
                if (product.getName().equals(productName)) {
                    foundProduct = product;
                    break;
                }
            }

            // 상품 검증
            if (foundProduct == null) {
                System.out.println("[ERROR] 존재하지 않는 상품입니다.");
                continue;
            }
            if (foundProduct.getQuantity() <= 0) {
                System.out.println("[ERROR] 재고가 없습니다.");
                continue;
            }
            if (userMoney < foundProduct.getPrice()) {
                System.out.println("[ERROR] 금액이 부족합니다.");
                continue;
            }

            // 구매 처리
            foundProduct.decreaseQuantity();
            userMoney -= foundProduct.getPrice();
        }

        // 7. 잔돈 반환 (보유 동전으로 최대한 반환)
        // 핵심: Math.min(보유개수, 필요개수) - 가능한 만큼만 반환
        Map<Coin, Integer> change = new HashMap<>();

        for (Coin coin : Coin.values()) {
            int coinAmount = coin.getAmount();
            int available = coinCount.get(coin);  // 보유 개수
            int needed = userMoney / coinAmount;   // 필요 개수

            // 보유 vs 필요 중 작은 값 = 실제 사용 개수
            int useCount = Math.min(available, needed);

            change.put(coin, useCount);
            userMoney -= (useCount * coinAmount);  // 남은 금액 차감
        }

        // 8. 반환된 동전 출력 (0개인 동전은 출력 안 함)
        System.out.println("잔돈");
        for (Coin coin : Coin.values()) {
            int count = change.get(coin);
            if (count > 0) {
                System.out.println(coin.getAmount() + "원 - " + count + "개");
            }
        }
    }
}
