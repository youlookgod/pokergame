package org.example.manager;

import org.example.model.Card;
import org.example.rule.CardRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description 卡牌管理器
 * @Author aiq
 * @Date 2024/3/15 14:09
 * @Version 1.0
 */
public class CardManager {
    /**
     * 牌
     */
    private List<Card> cardList;

    public CardManager() {
    }

    /**
     * 初始化纸牌
     *
     * @param rule 纸牌规则
     * @return
     */
    public List<Card> initCard(CardRule rule) {
        Objects.requireNonNull(rule);
        if (rule.getNumStart() < 1 || rule.getNumEnd() < 1 || rule.getNumEnd() <= rule.getNumStart()) {
            throw new IllegalArgumentException("牌面数值需大于1");
        }
        if (null == rule.getColors() || rule.getColors().size() == 0) {
            throw new IllegalArgumentException("纸牌花色不正确");
        }

        //准备一副扑克牌
        List<Card> cardList = new ArrayList<>();

        for (int i = rule.getNumStart(); i <= rule.getNumEnd(); i++) {
            for (String color : rule.getColors()) {
                cardList.add(new Card(color, i + "", i));
            }
        }

        if (rule.isNeedKing()) {
            // 大王
            cardList.add(new Card("K", "20", 20));
            // 小王
            cardList.add(new Card("Q", "20", 20));
        }
        this.cardList = cardList;
        return cardList;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
