package org.example.comparator;

import org.example.model.Card;
import org.example.rule.CardRule;

import java.util.*;

/**
 * @Description 牌大小比较器
 * @Author aiq
 * @Date 2024/3/15 21:45
 * @Version 1.0
 */
public class CardComparator implements Comparator<Card> {
    private Map<String, Integer> suitRank = new HashMap<>();

    public CardComparator(CardRule cardRule) {
        Objects.requireNonNull(cardRule, "纸牌规则不能为空");
        List<String> colors = cardRule.getColors();
        boolean needKing = cardRule.isNeedKing();

        //"K" 大王, "Q" 小王, "D" 黑桃, "C" 红桃, "B" 梅花, "A" 方片
        for (int i = 0; i < colors.size(); i++) {
            suitRank.put(colors.get(i), i + 1);
        }

        if (needKing) {
            // 大王
            suitRank.put("K", 6);
            // 小王
            suitRank.put("Q", 5);
        }
    }

    public CardComparator(List<String> colors, boolean needKing) {
        for (int i = 0; i < colors.size(); i++) {
            suitRank.put(colors.get(i), i + 1);
        }

        if (needKing) {
            // 大王
            suitRank.put("K", 6);
            // 小王
            suitRank.put("Q", 5);
        }
//        suitRank.put("K", 6); // 大王
//        suitRank.put("Q", 5); // 小王
//        suitRank.put("D", 4); // 黑桃
//        suitRank.put("C", 3); // 红桃
//        suitRank.put("B", 2); // 梅花
//        suitRank.put("A", 1); // 方片
    }

    @Override
    public int compare(Card card1, Card card2) {
        if (!card1.getPoint().equals(card2.getPoint())) {

            return card1.getPoint() - card2.getPoint();
        }

        return suitRank.get(card1.getColor()) - suitRank.get(card2.getColor());
    }

}
