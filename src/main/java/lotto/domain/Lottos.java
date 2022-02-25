package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        Objects.requireNonNull(lottos, "[ERROR] Lottos는 null로 생성할 수 없습니다.");
        this.lottos = new ArrayList<>(lottos);
        checkLottosIsEmpty(this.lottos);
    }

    private void checkLottosIsEmpty(List<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] Lottos는 lotto가 1개이상으로 생성되어야 합니다.");
        }
    }

    public LottoResult createResult(final WinLotto winLotto) {
        final Map<Rank, Integer> resultMap = Rank.createInitResultMap();
        lottos.stream()
                .map(winLotto::matchResult)
                .forEach(result -> rankCountUp(resultMap, result));
        return new LottoResult(resultMap);
    }

    private Integer rankCountUp(Map<Rank, Integer> resultMap, Rank result) {
        return resultMap.replace(result, resultMap.get(result) + 1);
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
