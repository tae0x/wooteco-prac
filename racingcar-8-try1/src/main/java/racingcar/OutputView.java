package racingcar;

public class OutputView {
    public static void printGameStart() {
        System.out.println("\n실행 결과");
    }


    public static void printCarStatus(String name, int position) {
        System.out.print(name + " : ");
        for (int k = 0; k < position; k++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printLine() {
        System.out.println();
    }

    public static void printWinner(String winnerNames) {
        System.out.println("최종 우승자 : " + winnerNames);
    }
}
