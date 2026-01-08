package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    public static int readPurchaseAmount() {
        int lottoCount = 0;

        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            try {
                String input = Console.readLine();
                int purchaseAmount = Integer.parseInt(input);

                if (purchaseAmount <= 0 || purchaseAmount % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입금액은 1,000원 단위여야 합니다.");
                }

                lottoCount = purchaseAmount / 1000;
                break;

            } catch (NumberFormatException e) {
                OutputView.printError("[ERROR] 구입 금액은 숫자만 가능합니다.");

            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
        return lottoCount;
    }

    public static Lotto readWinningNumber() {
        Lotto winningLotto = null;

        while (true) {
            try {
                System.out.println("\n당첨 번호를 입력해주세요.");
                String input = Console.readLine();
                String[] tokens = input.split(",");

                List<Integer> winningNumbers = new ArrayList<>();

                for (String token : tokens) {

                    int winningNumber = Integer.parseInt(token.trim());

                    if (winningNumber < 1 || winningNumber > 45) {
                        throw new IllegalArgumentException("[ERROR] 당첨 번호는 1 ~ 45 사이여야합니다.");
                    }

                    winningNumbers.add(winningNumber);
                }

                winningLotto = new Lotto(winningNumbers);
                break;

            } catch (NumberFormatException e) {
                OutputView.printError("[ERROR] 당첨 번호는 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
        return winningLotto;
    }

    public static int readBonusNumber(Lotto winningLotto) {
        int bonusNumber;

        while (true) {
            try {
                System.out.println("\n보너스 번호를 입력해 주세요.");
                String input = Console.readLine();

                bonusNumber = Integer.parseInt(input);
                if (bonusNumber < 1 || bonusNumber > 45) {
                    throw new IllegalArgumentException("[ERROR] 당첨 번호는 1 ~ 45 사이여야합니다.");
                }

                if (winningLotto.contains(bonusNumber)) {
                    throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
                }

                break;
            } catch (NumberFormatException e) {
                OutputView.printError("[ERROR] 구입 금액은 숫자만 가능합니다.");
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
        return bonusNumber;
    }
}
