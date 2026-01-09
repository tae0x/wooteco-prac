package racingcar;

import java.util.List;

public class OutputView {
    public static void printRaceStart() {
        System.out.println("\n실행 결과");
    }

    public static void printCarStatus(Car car) {
        System.out.println(car);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printWinners(List<Car> winningCars) {
        System.out.print("최종 우승자 : ");

        for (int i = 0; i < winningCars.size(); i++) {

            System.out.print(winningCars.get(i).getName());
            if (i < winningCars.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
