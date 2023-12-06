package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.enums.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

  private Lottos lottos;
  private Lotto resultLotto;
  private LottoGame lottoGame;
  @BeforeEach
  void setting_lotto() {

    Set<LottoNumber> threeMatchLottoNumbers =  new HashSet<>(List.of(
        LottoNumber.of(5), LottoNumber.of(25)
        , LottoNumber.of(30), LottoNumber.of(8)
        , LottoNumber.of(2), LottoNumber.of(43)
    ));
    Set<LottoNumber> threeMatchLottoNumbers2 =  new HashSet<>(List.of(
        LottoNumber.of(5), LottoNumber.of(25)
        , LottoNumber.of(30), LottoNumber.of(8)
        , LottoNumber.of(2), LottoNumber.of(43)
    ));
    Lotto threeMatchLotto = Lotto.defaultOf(threeMatchLottoNumbers);
    Lotto threeMatchLotto2 = Lotto.defaultOf(threeMatchLottoNumbers2);

    Set<LottoNumber> fourMatchLottoNumbers = new HashSet<>(List.of(
        LottoNumber.of(5), LottoNumber.of(25)
        , LottoNumber.of(30), LottoNumber.of(8)
        , LottoNumber.of(2), LottoNumber.of(40)
    ));
    Lotto fourMatchLotto = Lotto.defaultOf(fourMatchLottoNumbers);
    lottos = Lottos.of(List.of(threeMatchLotto,threeMatchLotto2, fourMatchLotto));

    Set<LottoNumber> lottoResultNumbers =  new HashSet<>(List.of(
        LottoNumber.of(5), LottoNumber.of(25)
        , LottoNumber.of(30), LottoNumber.of(6)
        , LottoNumber.of(1), LottoNumber.of(40)
    ));

    lottoGame = LottoGame.defaultOf(lottos, new WinningLotto("5,25,30,6,1,40", 8));
    resultLotto = Lotto.defaultOf(lottoResultNumbers);
  }

  @Test
  @DisplayName("Lotto중에 매칭 개수되는 Lotto들 카운팅하기")
  public void count_matching() {
    // given
    LottoResult lottoResult = lottoGame.resultWithBonusNumber();

    // when
    int result = lottoResult.findMatchResultCount(Rank.THREE);

    // then
    assertThat(result).isEqualTo(2);
  }

  @Test
  @DisplayName("Lotto중에 매칭 개수되는 Lotto들 카운팅하기")
  public void bonus_number_matching() {
    // given
    LottoResult lottoResult = lottoGame.resultWithBonusNumber();

    // when
    int result = lottoResult.findMatchResultCount(Rank.THREE);

    // then
    assertThat(result).isEqualTo(2);
  }

  @Test
  @DisplayName("수익률 계산 기능")
  public void caculate_profit_rate() throws Exception {
    // given
    int amount = resultLotto.size() * 1000;
    LottoResult lottoResult = lottoGame.resultWithBonusNumber();

    // when
    double profitRate = lottoResult.calculateProfitRate(amount);

    // then
    Assertions.assertThat(profitRate).isEqualTo(10.0);
  }
}
