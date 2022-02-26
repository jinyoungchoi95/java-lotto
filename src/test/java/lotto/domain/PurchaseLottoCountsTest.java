package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseLottoCountsTest {

    @DisplayName("수동 구매 갯수가 음수인 경우 예외가 발생한다.")
    @Test
    void createExceptionByNegativeManualCounts() {
        final int manualCount = -1;
        final int automaticCount = 5;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PurchaseLottoCounts(manualCount, automaticCount))
                .withMessage("[ERROR] 구매 갯수는 음수가 들어올 수 없습니다.");
    }

    @DisplayName("자동 구매 갯수가 음수인 경우 예외가 발생한다.")
    @Test
    void createExceptionByNegativeAutomaticCounts() {
        final int manualCount = 5;
        final int automaticCount = -1;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PurchaseLottoCounts(manualCount, automaticCount))
                .withMessage("[ERROR] 구매 갯수는 음수가 들어올 수 없습니다.");
    }
}