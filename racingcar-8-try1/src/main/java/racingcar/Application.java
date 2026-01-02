package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputNames = Console.readLine();

        String[] names = inputNames.split(",");

        for (String name : names) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
            }
        }

        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputCount = Console.readLine();
        int tryCount;

        try {
            tryCount = Integer.parseInt(inputCount);
            if (tryCount <= 0) {
                throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }

        int[] positions = new int[names.length];

        System.out.println("\n실행 결과");
        for (int i = 0; i < tryCount; i++) {
            for (int j = 0; j < names.length; j++) {
                int randomValue = Randoms.pickNumberInRange(0, 9);
                if (randomValue >= 4) {
                    positions[j]++;
                }
                System.out.print(names[j] + " : ");
                for (int k = 0; k < positions[j]; k++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.println();
        }

        int maxPostion = 0;
        for (int position : positions) {
            if (position > maxPostion) {
                maxPostion = position;
            }
        }

        StringBuilder winners = new StringBuilder();

        for (int i = 0; i < names.length; i++) {
            if (positions[i] == maxPostion) {

                if (winners.length() > 0) {
                    winners.append(", ");
                }
                winners.append(names[i]);
            }
        }
        System.out.println("최종 우승자 : " + winners);
    }
}
