package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.utill.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.view.UserInterface.*;


public class LottoController {

    public static void runLottoGame() {
        final int lottoCount = getLottoCount(purchaseLotto());
        final List<Lotto> lottos = getLottoList(lottoCount);
        printLottos(lottos);
        final WinningLotto winningLotto = getWinningLotto();
        YieldResult lottoResult = new YieldResult(winningLotto);
        lottoResult.calcRank(lottos);
        printWinningResult(lottoResult, lottoCount * 1000);
    }


    static int getLottoCount(String purchaseLotto) {
        if(purchaseLotto.length() == 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액을 입력하지 않았습니다.");
        }
        try {
            int value = Integer.parseInt(purchaseLotto);
            if(value % 1000 != 0) {
                throw new IllegalArgumentException("[ERROR] 1,000원 단위로 입력해야 합니다.");
            }
            return value / 1000;
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 숫자만 입력가능합니다.");
        }
    }
    private static List<Lotto> getLottoList(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>(lottoCount);
        for (int add = 0; add < lottoCount; add ++) {
            lottos.add(makeLotto());
        }
        return lottos;
    }

    private static Lotto makeLotto() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1,45,6));
        numbers.sort(Comparator.naturalOrder());
        return new Lotto(numbers);
    }

    private static WinningLotto getWinningLotto() {
        Lotto lotto = getWinningLottoNumber(inputWinningNumber());
        int bonus = Integer.parseInt(inputWinningBonus());
        return new WinningLotto(lotto, bonus);
    }

    private static Lotto getWinningLottoNumber(String string_input) {
        List<String> string_str = Arrays.asList(string_input.split(","));
        List<Integer> int_number = string_str.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        return new Lotto(int_number);
    }
}