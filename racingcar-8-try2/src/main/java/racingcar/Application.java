package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputCarNames = Console.readLine();

        String[] carNames = inputCarNames.split(",");
        for (String carName : carNames) {

            if(carName.length() > 5) {
                throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
            }
        }

        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputTryCount = Console.readLine();
        int tryCount;
        try {
            tryCount = Integer.parseInt(inputTryCount);
            if(tryCount <= 0) {
                throw new IllegalArgumentException("시도 횟수는 1 이상만 가능합니다.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력 가능합니다.");
        }

        int[] carPositions = new int[carNames.length];

        System.out.println("\n실행 결과");
        for (int i = 0; i < tryCount; i++) {
            for (int j = 0; j < carNames.length; j++) {

                int randomValue = Randoms.pickNumberInRange(0, 9);

                if(randomValue >= 4) {
                    carPositions[j]++;
                }

                System.out.print(carNames[j] + " : ");
                for (int k = 0; k < carPositions[j]; k++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.println();
        }

        int maxPosition = 0;
        for (int carPosition : carPositions) {
            if(carPosition > maxPosition) {
                maxPosition = carPosition;
            }
        }

        StringBuilder winners = new StringBuilder();

        for (int i = 0; i < carNames.length; i++) {
            if(carPositions[i] == maxPosition) {

                if(winners.length() > 0) {
                    winners.append(" ,");
                }

                winners.append(carNames[i]);
            }
        }

        System.out.println("최종 우승자 : " + winners);
    }
}
