package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        String[] carNames = InputView.readCarNames();

        int tryCount = InputView.readTryCount();

        int[] carPositions = new int[carNames.length];

        System.out.println("\n실행 결과");
        for (int i = 0; i < tryCount; i++) {
            for (int j = 0; j < carNames.length; j++) {

                int randomValue = Randoms.pickNumberInRange(0, 9);

                if (randomValue >= 4) {
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
            if (carPosition > maxPosition) {
                maxPosition = carPosition;
            }
        }

        StringBuilder winners = new StringBuilder();

        for (int i = 0; i < carNames.length; i++) {
            if (carPositions[i] == maxPosition) {

                if (winners.length() > 0) {
                    winners.append(" ,");
                }

                winners.append(carNames[i]);
            }
        }

        System.out.println("최종 우승자 : " + winners);
    }


}
