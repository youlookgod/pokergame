package org.example.judge;

import org.example.manager.CardManager;
import org.example.manager.PlayerManager;
import org.example.model.Card;
import org.example.model.Player;
import org.example.res.GroupCardResult;
import org.example.rule.CardRule;

import java.util.*;

/**
 * @Description 纸牌抽象裁判
 * @Author aiq
 * @Date 2024/3/15 15:20
 * @Version 1.0
 */
public abstract class AbstractPokerJudge implements CardJudge {
    private CardManager cardManager;
    private PlayerManager playerManager;

    private Comparator<Card> judgeComparator;

    public Comparator<Card> getJudgeComparator() {
        return judgeComparator;
    }

    public void setJudgeComparator(Comparator<Card> judgeComparator) {
        this.judgeComparator = judgeComparator;
    }

    public AbstractPokerJudge(CardManager cardManager, PlayerManager playerManager, Comparator<Card> judgeComparator) {
        Objects.requireNonNull(cardManager,"卡牌管理器不能为空");
        Objects.requireNonNull(playerManager,"玩家管理器不能为空");
        Objects.requireNonNull(cardManager,"牌面大小比较器不能为空");
        this.cardManager = cardManager;
        this.playerManager = playerManager;
        this.judgeComparator = judgeComparator;
    }

    @Override
    public void shufflePoker(List<Card> cardList) {
        Collections.shuffle(cardList);
    }

    @Override
    public void dealPokerToPlayer(List<Card> cardList, List<Player> playerList) {

    }

    @Override
    public abstract GroupCardResult judgement(List<Player> playerList);

    @Override
    public GroupCardResult start(CardRule rule, List<String> playerNameList) {
        //初始化纸牌 并把牌放在cardManager(相当于牌桌)里
//        List<Card> cardList = cardManager.init(rule);
        cardManager.initCard(rule);

        //初始化玩家
//        List<Player> playerList = playerManager.initPlayers(playerNameList);
        playerManager.initPlayers(playerNameList);

        //洗牌，裁判在牌桌上洗牌
        shufflePoker(cardManager.getCardList());

        //发牌
        dealPokerToPlayer(cardManager.getCardList(), playerManager.getPlayerList());

        //可以出多少轮
        int round = playerManager.getPlayerList().get(0).getHandCards().size();
        for (int i = 0; i < round; i++) {
            //玩家每轮出牌结果
            Map<Player, Card> roundCardMap = playerManager.playCard(i + 1, getJudgeComparator());

            //裁判做一轮牌局处理，包括选出本轮获胜玩家、为获胜玩家加分，收回已经打出去的牌并放入玩家已出牌列表
            roundJudgement(playerManager.getPlayerList(), roundCardMap);
        }

        //裁判算分并得到最终结果
        return judgement(playerManager.getPlayerList());

    }

    @Override
    public void roundJudgement(List<Player> playerList, Map<Player, Card> roundCardMap) {
//        //玩家每轮出牌策略
//        Map<Player, Card> roundCardMap = roundCardStrategy(playerList);

        //本轮获胜者
        Player winner = getWinner(roundCardMap);

        //为获胜者加分并回收打出的牌
        calculateScoreAndRemoveHandCard(winner, roundCardMap);

        //更新每个玩家出过的牌
        updatePlayCard(playerList, roundCardMap);
    }

//    /**
//     * 每轮出牌策略
//     *
//     * @param playerList
//     * @return
//     */
//    public abstract Map<Player, Card> roundCardStrategy(List<Player> playerList);

    /**
     * 获取本轮获胜者
     * 其实也可以在这里写默认逻辑，因为有牌大小比较器，所以知道这一轮中谁获胜了
     *
     * @param roundCardMap
     * @return
     */
    public abstract Player getWinner(Map<Player, Card> roundCardMap);

    /**
     * 本轮算分并从玩家中删除本轮出的牌
     * 可以由子类覆盖
     *
     * @param winner
     * @param roundCardMap
     */
    public void calculateScoreAndRemoveHandCard(Player winner, Map<Player, Card> roundCardMap) {
        roundCardMap.forEach((player, card) -> {
            //本轮胜者加分
            winner.incScore(card.getPoint());
            //已出的牌回收
            player.removeCard(card);
        });
    }

    /**
     * 更新玩家出牌
     *
     * @param playerList
     * @param roundCardMap
     */
    public void updatePlayCard(List<Player> playerList, Map<Player, Card> roundCardMap) {
        for (Player player : playerList) {
            Card card = roundCardMap.get(player);
            player.addToPlayCard(card);
        }
    }


}
