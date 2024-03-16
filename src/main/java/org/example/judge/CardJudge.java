package org.example.judge;

import org.example.model.Card;
import org.example.model.Player;
import org.example.res.GroupCardResult;
import org.example.rule.CardRule;

import java.util.List;
import java.util.Map;

/**
 * @Description 纸牌裁判
 * @Author aiq
 * @Date 2024/3/16 0:52
 * @Version 1.0
 */
public interface CardJudge extends Judge {
    /**
     * 洗牌
     */
    void shufflePoker(List<Card> cardList);

    /**
     * 发牌
     */
    void dealPokerToPlayer(List<Card> cardList, List<Player> playerList);

    /**
     * 游戏决胜
     */
    GroupCardResult judgement(List<Player> playerList);

    /**
     * 牌局开始
     *
     * @param rule
     * @param playerNameList 玩家
     * @return
     */
    GroupCardResult start(CardRule rule, List<String> playerNameList);

    /**
     * 每轮决胜
     *
     * @param playerList 所有玩家
     */
    void roundJudgement(List<Player> playerList, Map<Player, Card> roundCardMap);
}
