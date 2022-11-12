package lotto.utill;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

public class MakeLottoNumber {
    private final List<Integer> numbers;

    public MakeLottoNumber() {
        numbers = pickUniqueNumbersInRange(1, 45, 6);
    }

    List<Integer> makeLottoNumbers() {
        shuffleNumbers();
        return numbers.stream()
                .limit(6)
                .sorted()
                .collect(Collectors.toList());
    }

    // 랜덤으로 구성
    private void shuffleNumbers() {
        Collections.shuffle(numbers);
    }
}
