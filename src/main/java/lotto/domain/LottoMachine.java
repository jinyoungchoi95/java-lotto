package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final List<LottoNumber> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    private LottoMachine() {
    }

    public static Lottos buyLotto(final List<List<Integer>> manualNumbers, final PurchaseLottoCounts purchaseCounts) {
        Objects.requireNonNull(manualNumbers, "[ERROR] 수동 구매 로또는 null이 들어올 수 없습니다.");
        final List<Lotto> lottos = createLottos(new ArrayList<>(manualNumbers));
        Objects.requireNonNull(purchaseCounts, "[ERROR] 로또 구매 갯수는 null이 들어올 수 없습니다.");

        return addRandomLottos(lottos, purchaseCounts);
    }

    private static List<Lotto> createLottos(final List<List<Integer>> manualLottoNumbers) {
        return manualLottoNumbers.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());
    }

    private static Lottos addRandomLottos(final List<Lotto> manuallottos, final PurchaseLottoCounts purchaseCounts) {
        checkManualLottoSize(manuallottos, purchaseCounts);
        manuallottos.addAll(createRandomLottosByAutomaticCounts(purchaseCounts.getAutomaticCount()));
        return new Lottos(manuallottos);
    }

    private static void checkManualLottoSize(List<Lotto> manuallottos, PurchaseLottoCounts purchaseCounts) {
        if (!purchaseCounts.isManualSize(manuallottos.size())) {
            throw new IllegalArgumentException("[ERROR] 수동 로또 번호 갯수가 입력된 수동 구매 갯수와 다릅니다.");
        }
    }

    private static List<Lotto> createRandomLottosByAutomaticCounts(final int automaticLottoCounts) {
        return IntStream.range(0, automaticLottoCounts)
                .mapToObj(count -> createRandomLotto())
                .collect(Collectors.toList());
    }

    private static Lotto createRandomLotto() {
        return new Lotto(createRandomLottoNumbers());
    }

    private static List<LottoNumber> createRandomLottoNumbers() {
        Collections.shuffle(LOTTO_NUMBERS);
        return IntStream.range(0, Lotto.LOTTO_NUMBER_SIZE_STANDARD)
                .boxed()
                .map(LOTTO_NUMBERS::get)
                .sorted()
                .collect(Collectors.toList());
    }
}
