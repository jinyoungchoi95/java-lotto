package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("구매 로또 생성시 숫자가 6개가 입력되지 않으면 에러 발생")
    @Test
    void lottoCreateExceptionBySize() {
        final List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("[ERROR] 로또 넘버는 6개가 입력되어야 합니다.");
    }
}
