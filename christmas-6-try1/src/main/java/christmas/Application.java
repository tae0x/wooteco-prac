package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // 1. 인사 출력
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        // 2. 날짜 입력
        int visitDate;
        while (true) {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            String input = Console.readLine();

            try {
                visitDate = Integer.parseInt(input);

                if (visitDate < 1 || visitDate > 31) {
                    throw new IllegalArgumentException("[ERROR] 유요하지 않은 날짜입니다.");
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜 입니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 3. 메뉴 입력

        // 4. 할인 전 총금액 계산

        // 5. 디데이 할인

        // 6. 평일/주말 할인

        // 7. 특별 할인

        // 8. 증정 이벤트

        // 9. 총혜택/결제금액

        // 10. 배지

        // 11. 출력
    }
}
