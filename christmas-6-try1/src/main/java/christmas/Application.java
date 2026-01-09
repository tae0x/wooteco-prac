package christmas;

import camp.nextstep.edu.missionutils.Console;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // ===== 1. 인사 출력 =====
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        // ===== 2. 방문 날짜 입력 (1~31 검증, 재입력) =====
        int visitDate;
        while (true) {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            String input = Console.readLine();

            try {
                visitDate = Integer.parseInt(input);

                // 1~31 범위 검증
                if (visitDate < 1 || visitDate > 31) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                }
                break;  // 정상 입력 시 while 탈출

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // ===== 3. 주문 메뉴 입력 =====
        // 핵심: split(",") → split("-") → Menu.findByName()로 검증
        Map<Menu, Integer> orders = new HashMap<>();

        while (true) {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            String input = Console.readLine();

            try {
                String[] items = input.split(",");  // 쉼표로 메뉴 분리
                orders.clear();  // 재시도 시 초기화

                for (String item : items) {
                    String[] parts = item.split("-");  // 하이픈으로 이름-개수 분리
                    String menuName = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());

                    // 메뉴판에 있는지 검증 (Menu Enum에서 찾기)
                    Menu menu = Menu.findByName(menuName);

                    // 중복 메뉴 체크
                    if (orders.containsKey(menu)) {
                        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }

                    // 개수 1 이상 체크
                    if (quantity < 1) {
                        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }

                    orders.put(menu, quantity);
                }

                // 음료만 주문 체크 (모든 메뉴가 BEVERAGE면 에러)
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

                break;  // 모든 검증 통과 시 while 탈출

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }

        // ===== 4. 할인 전 총주문 금액 계산 =====
        // 핵심: 메뉴 가격 × 개수의 합
        int totalAmount = 0;
        for (Menu menu : orders.keySet()) {
            int quantity = orders.get(menu);
            totalAmount += menu.getPrice() * quantity;
        }

        // 10,000원 미만이면 이벤트 적용 안 함 (나중에 처리)

        // ===== 5. 크리스마스 디데이 할인 (1~25일만) =====
        // 핵심: 1,000 + (날짜 - 1) × 100
        int christmasDiscount = 0;
        if (visitDate >= 1 && visitDate <= 25) {
            christmasDiscount = 1000 + (visitDate - 1) * 100;
        }

        // ===== 6. 평일/주말 할인 =====
        // 핵심: LocalDate로 요일 판단 → 평일(일~목): 디저트 할인, 주말(금토): 메인 할인
        LocalDate date = LocalDate.of(2023, 12, visitDate);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 주말: 금요일(FRIDAY), 토요일(SATURDAY)
        boolean isWeekend = (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY);

        int weekdayDiscount = 0;
        int weekendDiscount = 0;

        for (Menu menu : orders.keySet()) {
            int quantity = orders.get(menu);

            // 평일 + 디저트 → 메뉴 1개당 2,023원 할인
            if (!isWeekend && menu.getCategory().equals("DESSERT")) {
                weekdayDiscount += 2023 * quantity;
            }

            // 주말 + 메인 → 메뉴 1개당 2,023원 할인
            if (isWeekend && menu.getCategory().equals("MAIN")) {
                weekendDiscount += 2023 * quantity;
            }
        }

        // ===== 7. 특별 할인 (별 있는 날) =====
        // 핵심: 일요일(SUNDAY) 또는 크리스마스(25일) → 1,000원 할인
        int specialDiscount = 0;
        if (dayOfWeek == DayOfWeek.SUNDAY || visitDate == 25) {
            specialDiscount = 1000;
        }

        // ===== 8. 증정 이벤트 =====
        // 핵심: 할인 전 총금액 120,000원 이상 → 샴페인 1개 (25,000원)
        boolean hasGift = (totalAmount >= 120000);
        int giftPrice = 0;
        if (hasGift) {
            giftPrice = 25000;  // 샴페인 가격
        }

        // ===== 9. 총혜택 금액 / 할인 후 결제 금액 =====
        // 핵심:
        // - 총혜택 = 할인들의 합 + 증정 가격
        // - 결제 금액 = 할인 전 금액 - 할인들의 합 (증정 가격은 빼지 않음!)
        int totalDiscount = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
        int totalBenefit = totalDiscount + giftPrice;  // 증정 포함
        int finalAmount = totalAmount - totalDiscount;  // 증정 제외!

        // ===== 10. 배지 판정 =====
        // 핵심: 총혜택 금액 기준 (5천원: 별, 1만원: 트리, 2만원: 산타)
        String badge = "없음";
        if (totalBenefit >= 20000) {
            badge = "산타";
        }
        if (totalBenefit >= 10000 && totalBenefit < 20000) {
            badge = "트리";
        }
        if (totalBenefit >= 5000 && totalBenefit < 10000) {
            badge = "별";
        }

        // ===== 11. 결과 출력 =====
        System.out.println("12월 " + visitDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();

        // 주문 메뉴
        System.out.println("<주문 메뉴>");
        for (Menu menu : orders.keySet()) {
            System.out.println(menu.getName() + " " + orders.get(menu) + "개");
        }
        System.out.println();

        // 할인 전 총주문 금액 (천 단위 쉼표)
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", totalAmount));
        System.out.println();

        // 증정 메뉴
        System.out.println("<증정 메뉴>");
        if (hasGift) {
            System.out.println("샴페인 1개");
        } else {
            System.out.println("없음");
        }
        System.out.println();

        // 혜택 내역 (0원 초과인 것만 출력)
        System.out.println("<혜택 내역>");
        if (totalBenefit == 0) {
            System.out.println("없음");
        } else {
            if (christmasDiscount > 0) {
                System.out.println("크리스마스 디데이 할인: -" + String.format("%,d원", christmasDiscount));
            }
            if (weekdayDiscount > 0) {
                System.out.println("평일 할인: -" + String.format("%,d원", weekdayDiscount));
            }
            if (weekendDiscount > 0) {
                System.out.println("주말 할인: -" + String.format("%,d원", weekendDiscount));
            }
            if (specialDiscount > 0) {
                System.out.println("특별 할인: -" + String.format("%,d원", specialDiscount));
            }
            if (hasGift) {
                System.out.println("증정 이벤트: -" + String.format("%,d원", giftPrice));
            }
        }
        System.out.println();

        // 총혜택 금액 (항상 마이너스 부호)
        System.out.println("<총혜택 금액>");
        System.out.println("-" + String.format("%,d원", totalBenefit));
        System.out.println();

        // 할인 후 예상 결제 금액
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원", finalAmount));
        System.out.println();

        // 12월 이벤트 배지
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }
}