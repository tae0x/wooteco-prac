package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        String[] names = inputCarNames();

        int tryCount = inputTryCount();

        int[] positions = new int[names.length];

        playGame(tryCount, names, positions);

        printWinner(names, positions);
    }

    private static void printWinner(String[] names, int[] positions) {
        int maxPosition = findMaxPosition(positions);

        StringBuilder winners = new StringBuilder();

        for (int i = 0; i < names.length; i++) {
            if (positions[i] == maxPosition) {

                if (winners.length() > 0) {
                    winners.append(", ");
                }
                winners.append(names[i]);
            }
        }
        System.out.println("최종 우승자 : " + winners);
    }

    private static int findMaxPosition(int[] positions) {
        int maxPosition = 0;
        for (int position : positions) {
            if (position > maxPosition) {
                maxPosition = position;
            }
        }
        return maxPosition;
    }

    private static void playGame(int tryCount, String[] names, int[] positions) {
        System.out.println("\n실행 결과");
        for (int i = 0; i < tryCount; i++) {
            playOneRound(names, positions);
            System.out.println();
        }
    }

    private static void playOneRound(String[] names, int[] positions) {
        for (int j = 0; j < names.length; j++) {
            moveCar(positions, j);
            printCarStatus(names[j], positions[j]);
        }
    }

    private static void moveCar(int[] positions, int index) {
        int randomValue = Randoms.pickNumberInRange(0, 9);
        if (randomValue >= 4) {
            positions[index]++;
        }
    }

    private static void printCarStatus(String name, int position) {
        System.out.print(name + " : ");
        for (int k = 0; k < position; k++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static String[] inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputNames = Console.readLine();
        String[] names = inputNames.split(",");

        validateNames(names);

        return names;
    }

    private static void validateNames(String[] names) {
        for (String name : names) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
            }
        }
    }

    private static int inputTryCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputCount = Console.readLine();

        int tryCount = parseToInt(inputCount);

        validateTryCount(tryCount);

        return tryCount;
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    private static void validateTryCount(int tryCount) {
        if (tryCount <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }


}
