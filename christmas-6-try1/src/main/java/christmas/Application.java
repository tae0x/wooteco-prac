package christmas;

import camp.nextstep.edu.missionutils.Console;

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
