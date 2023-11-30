package lotto.domain;

public class LottoGame {
  private final Lotto resultLotto;

  private LottoGame(Lotto resultLotto) {
    this.resultLotto = resultLotto;
  }

  public static LottoGame defaultOf(String resultLottoNumbers) {
    return new LottoGame(Lotto.defaultOf(resultLottoNumbers));
  }

  public LottoResult result(Lottos lottos){
    return LottoMachine.match(resultLotto, lottos);
  }
}
