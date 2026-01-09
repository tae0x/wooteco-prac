package lotto;

import camp.nextstep.edu.missionutils.Console;

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

        // 3. 당첨 번호 입력

        // 4. 보너스 번호 입력

        // 5. 당첨 계산 및 통계 

        // 6. 결과 출력
    }
}
