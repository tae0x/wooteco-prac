package christmas;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // 1. 인사 출력
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        // 2. 방문 날짜 입력
        int visitDate;
        while (true) {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            String input = Console.readLine();

            try {
                visitDate = Integer.parseInt(input);

                if (visitDate < 1 || visitDate > 31) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 3. 주문 메뉴 입력
        Map<Menu, Integer> orders = new HashMap<>();

        while (true) {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            String input = Console.readLine();

            try {
                String[] items = input.split(",");
                orders.clear();  // 재시도 시 초기화

                for (String item : items) {
                    String[] parts = item.split("-");
                    String menuName = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());

                    // 메뉴 찾기 (없으면 예외 발생)
                    Menu menu = Menu.findByName(menuName);

                    // 중복 체크
                    if (orders.containsKey(menu)) {
                        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }

                    // 개수 1 이상
                    if (quantity < 1) {
                        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }

                    orders.put(menu, quantity);
                }

                // 음료만 주문 체크
                boolean allBeverage = true;
                for (Menu menu : orders.keySet()) {
                    if (!menu.getCategory().equals("BEVERAGE")) {
                        allBeverage = false;
                        break;
                    }
                }

                if (allBeverage) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                }

                // 총 개수 20개 초과 체크
                int totalCount = 0;
                for (int quantity : orders.values()) {
                    totalCount += quantity;
                }
                if (totalCount > 20) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }

        // 4. 할인 전 총주문 금액 계산

        // 5. 크리스마스 디데이 할인

        // 6. 평일/주말 할인

        // 7. 특별 할인

        // 8. 증정 이벤트

        // 9. 총혜택 금액 / 할인 후 결제 금액

        // 10. 배지 판정

        // 11. 결과 출력
    }
}
