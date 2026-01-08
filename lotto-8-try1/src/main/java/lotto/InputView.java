package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    public static int inputBonusNumber(List<Integer> correctNumbers) {
        int bonusNumber;
        while (true) {
            try {
                System.out.println("\n보너스 번호를 입력해주세요.");
                String inputBonusNumber = Console.readLine();
                int tempBonus = Integer.parseInt(inputBonusNumber);

                if (tempBonus < 1 || tempBonus > 45) {
                    throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
                }

                if (correctNumbers.contains(tempBonus)) {
                    throw new IllegalArgumentException("[ERROR] 당첨 번호와 중복될 수 없습니다.");
                }

                bonusNumber = tempBonus;
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonusNumber;
    }

    public static List<Integer> inputWinningNumbers() {
        List<Integer> correctNumbers = null;
        while (true) {
            try {
                System.out.println("\n당첨 번호를 입력해 주세요.");
                String inputCorrectNumber = Console.readLine();

                String[] split = inputCorrectNumber.split(",");
                List<Integer> tempNumbers = new ArrayList<>();

                for (String s : split) {
                    tempNumbers.add(Integer.parseInt(s.trim()));
                }

                Lotto verifyLotto = new Lotto(tempNumbers);
                correctNumbers = tempNumbers;

                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력해 주세요");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return correctNumbers;
    }

    public static int inputPurchaseAmount() {
        int amount = 0;
        while (true) {
            try {
                System.out.println("구입 금액을 입력해 주세요.");
                String inputMoney = Console.readLine();
                amount = Integer.parseInt(inputMoney);

                if (amount % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 구입 금액은 숫자만 가능합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return amount;
    }
}
