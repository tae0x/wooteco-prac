package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        String[] names = InputView.readCarNames();
        int tryCount = InputView.readTryCount();

        int[] positions = new int[names.length];

        OutputView.printGameStart();
        playGame(tryCount, names, positions);

        String winners = findWinner(names, positions);
        OutputView.printWinner(winners);
    }

    private static String findWinner(String[] names, int[] positions) {
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
        return winners.toString();
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
        for (int i = 0; i < tryCount; i++) {
            playOneRound(names, positions);
            OutputView.printLine();
        }
    }

    private static void playOneRound(String[] names, int[] positions) {
        for (int j = 0; j < names.length; j++) {
            moveCar(positions, j);
            OutputView.printCarStatus(names[j], positions[j]);
        }
    }

    private static void moveCar(int[] positions, int index) {
        int randomValue = Randoms.pickNumberInRange(0, 9);
        if (randomValue >= 4) {
            positions[index]++;
        }
    }
}
