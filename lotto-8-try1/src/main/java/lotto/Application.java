package lotto;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        int purchaseAmount = InputView.inputPurchaseAmount();
        List<Lotto> lottos = LottoMachine.buyLottos(purchaseAmount);
        OutputView.printPurchaseResult(lottos);

        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber(winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoResult lottoResult = new LottoResult(lottos, winningLotto);

        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);
        OutputView.printWinningStatistics(lottoResult, profitRate);
    }
}



