package racingcar;

public class OutputView {
    public static void printGameStart() {
        System.out.println("\n실행 결과");
    }

    public static void printWinner(StringBuilder winners) {
        System.out.println("최종 우승자 : " + winners);
    }

    public static void printCarStatus(String carName, int carPosition) {
        System.out.print(carName + " : ");
        for (int k = 0; k < carPosition; k++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
