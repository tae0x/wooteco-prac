package vendingmachine;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // 1. 자판기 보유 금액 입력
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input = Console.readLine();
        int machineAmount = Integer.parseInt(input);

        // 2. 보유 금액으로 동전 랜덤 생성

        // 3. 생성된 동전 출력

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
