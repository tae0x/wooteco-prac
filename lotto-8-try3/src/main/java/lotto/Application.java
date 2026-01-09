package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // ===== 1. 구매 금액 입력 =====
        // 핵심: 1,000원 단위 검증, 양수 검증, 재입력
        int lottoCount;
        while (true) {
            System.out.println("구입금액을 입력해주세요.");
            String input = Console.readLine();

            try {
                int money = Integer.parseInt(input);

                // 양수이면서 1,000원 단위인지 검증
                if (money <= 0 || money % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000 단위입니다.");
                }

                lottoCount = money / 1000;  // 로또 개수 계산
                break;  // 정상 입력 시 while 탈출

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력하세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // ===== 2. 로또 발행 =====
        // 핵심: pickUniqueNumbersInRange로 중복 없는 6개 생성, 정렬 후 출력
        System.out.println("\n" + lottoCount + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            // 1~45 중 중복 없는 6개 랜덤 생성
            List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);

            // 불변 리스트를 가변 리스트로 변환 (정렬 위해)
            List<Integer> mutableNumbers = new ArrayList<>(randomNumbers);
            Collections.sort(mutableNumbers);  // 오름차순 정렬

            lottos.add(new Lotto(mutableNumbers));
            System.out.println(lottos.get(i));  // [8, 21, 23, 41, 42, 43] 형태 출력
        }

        // ===== 3. 당첨 번호 입력 =====
        // 핵심: split(",")으로 파싱, trim() 필수, Lotto 생성자가 중복/개수 검증
        Lotto winningLotto = null;  // while 밖에서 선언 (스코프)

        while (true) {
            System.out.println("\n당첨 번호를 입력해 주세요.");
            String input = Console.readLine();

            try {
                String[] tokens = input.split(",");  // 쉼표로 분리
                List<Integer> winningNumbers = new ArrayList<>();

                for (String token : tokens) {
                    int winningNumber = Integer.parseInt(token.trim());  // trim() 필수!

                    // 1~45 범위 검증
                    if (winningNumber < 1 || winningNumber > 45) {
                        throw new IllegalArgumentException("[ERROR] 1 ~ 45 사이의 숫자를 입력하세요.");
                    }

                    winningNumbers.add(winningNumber);
                }

                // Lotto 생성자가 6개, 중복 검증함
                winningLotto = new Lotto(winningNumbers);
                break;

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력하세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // ===== 4. 보너스 번호 입력 =====
        // 핵심: 당첨 번호와 중복 검증 (winningLotto.contains 사용)
        int bonusNumber;
        while (true) {
            System.out.println("\n보너스 번호를 입력해 주세요.");
            String input = Console.readLine();

            try {
                bonusNumber = Integer.parseInt(input.trim());

                // 1~45 범위 검증
                if (bonusNumber < 1 || bonusNumber > 45) {
                    throw new IllegalArgumentException("[ERROR] 1 ~ 45 사이의 숫자를 입력하세요.");
                }

                // 당첨 번호와 중복 검증
                if (winningLotto.contains(bonusNumber)) {
                    throw new IllegalArgumentException("[ERROR] 당첨 번호와 중복될 수 없습니다.");
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력하세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // ===== 5. 당첨 계산 및 통계 =====
        // 핵심: matchCount + bonusMatch로 Rank 판정, Map에 등수별 개수 집계
        Map<Rank, Integer> result = new HashMap<>();

        // 모든 등수 0개로 초기화
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        // 각 로또마다 등수 판정
        for (Lotto lotto : lottos) {
            int matchCount = lotto.matchCount(winningLotto);  // 일치 개수
            boolean bonusMatch = lotto.contains(bonusNumber);  // 보너스 일치 여부

            // 일치 개수 + 보너스로 등수 판정
            Rank rank = Rank.valueOf(matchCount, bonusMatch);

            // 해당 등수 개수 +1
            result.put(rank, result.get(rank) + 1);
        }

        // 수익률 계산
        // 핵심: 총 상금 / 구입 금액 * 100
        int totalPrize = 0;
        for (Rank rank : result.keySet()) {
            totalPrize += rank.getPrize() * result.get(rank);  // 상금 * 당첨 개수
        }
        double profitRate = (double) totalPrize / (lottoCount * 1000) * 100;

        // ===== 6. 결과 출력 =====
        // 핵심: printf로 소수점 첫째자리까지 출력
        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + result.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + result.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.get(Rank.FIRST) + "개");
        System.out.printf("총 수익률은 %.1f%%입니다.\n", profitRate);  // %.1f = 소수점 첫째자리
    }
}