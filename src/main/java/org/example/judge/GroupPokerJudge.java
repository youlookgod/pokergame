package org.example.judge;

import com.google.common.base.Joiner;
import org.example.manager.CardManager;
import org.example.manager.PlayerManager;
import org.example.model.Card;
import org.example.model.Player;
import org.example.res.GroupCardResult;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 组队局纸牌裁判。
 * @Author aiq
 * @Date 2024/3/15 14:36
 * @Version 1.0
 */
public class GroupPokerJudge extends AbstractPokerJudge {

    public GroupPokerJudge(CardManager cardManager, PlayerManager playerManager, Comparator<Card> judgeComparator) {
        super(cardManager, playerManager, judgeComparator);
    }


    @Override
    public void dealPokerToPlayer(List<Card> cardList, List<Player> playerList) {
        //留两张底牌
        List<Card> cards = cardList.subList(0, cardList.size() - 2);
        List<Card> downCard = cardList.subList(cardList.size() - 2, cardList.size());
        System.out.println("底牌：" + Joiner.on(",").join(downCard.stream().map(s -> s.getColor() + s.getCardNo()).collect(Collectors.toList())));

        //顺序发牌
        for (int i = 0; i < cards.size(); i++) {
            playerList.get(i % playerList.size()).addToHand(cardList.get(i));
        }
    }

    @Override
    public GroupCardResult judgement(List<Player> playerList) {

        //玩家出牌
        Map<String, List<Card>> playerPlayCardMap = new LinkedHashMap<>();
        //玩家得分
        Map<String, Integer> playerScoreMap = new LinkedHashMap<>();
        for (Player player : playerList) {
            playerPlayCardMap.put(player.getName(), player.getPlayCards());
            playerScoreMap.put(player.getName(), player.getScore());
        }

        //最终获胜者
        Map<Integer, List<Player>> groupPlayerMap = playerList.stream().collect(Collectors.groupingBy(Player::getGroup));

        //判断平局
        Integer equalNum = 0;
        List<Player> winners = null;

        String groupPlayerInfo = "组队，";
        String groupScoreCount = "";
        for (Map.Entry<Integer, List<Player>> groupEntry : groupPlayerMap.entrySet()) {
            List<Player> players = groupEntry.getValue();
            groupPlayerInfo += players.get(0).getGroup() + "号队：" + Joiner.on("，").join(players.stream().map(Player::getName).collect(Collectors.toList())) + "  ";
            groupScoreCount += players.get(0).getGroup() + "号队得分：" + players.stream().mapToInt(Player::getScore).sum() + "  ";
            if (null == winners) {
                winners = players;
            } else {
                int tempGroupScore = winners.stream().mapToInt(Player::getScore).sum();
                int currentGroupScore = players.stream().mapToInt(Player::getScore).sum();
                if (currentGroupScore > tempGroupScore) {
                    winners = players;
                } else if (currentGroupScore == tempGroupScore) {
                    equalNum++;
                }
            }
        }

        String playerOrder = "拿/出牌顺序：" + Joiner.on("").join(playerList.stream().map(Player::getName).collect(Collectors.toList())) + "。";
        String sortAndGroup = playerOrder + groupPlayerInfo;

        //平局输出空
        String winner = groupScoreCount + "\n";
        winner += equalNum == groupPlayerMap.size() - 1 ? "得分相同：平局" : "得分最多：" + winners.get(0).getGroup() + "号队：" + Joiner.on(",").skipNulls().join(winners.stream().map(Player::getName).collect(Collectors.toList()));


        return new GroupCardResult(playerPlayCardMap, playerScoreMap, sortAndGroup, winner);
    }


    @Override
    public Player getWinner(Map<Player, Card> roundCardMap) {
        Card maxCard = null;
        Player maxCardPlayer = null;
        for (Map.Entry<Player, Card> playerCardEntry : roundCardMap.entrySet()) {
            Player player = playerCardEntry.getKey();
            Card card = playerCardEntry.getValue();
            if (null == maxCard) {
                //取最大的牌
                maxCard = card;
                maxCardPlayer = player;
            } else {
                int compare = getJudgeComparator().compare(card, maxCard);
                if (compare > 0) {
                    maxCard = card;
                    maxCardPlayer = player;
                }
            }
        }
        return maxCardPlayer;
    }


//    /**
//     * 基于分组的出牌策略 这里可以用策略模式定义多种策略
//     *
//     * @param playerList
//     * @return
//     */
//    @Override
//    public Map<Player, Card> roundCardStrategy(List<Player> playerList) {
//        Map<Integer, List<Player>> groupPlayerMap = playerList.stream().collect(Collectors.groupingBy(Player::getGroup));
//
//        Map<Player, Card> roundPlayerAndCard = new HashMap<>();
//        groupPlayerMap.forEach((g, list) -> {
//
//            //获取当前组持有最大牌的玩家
//            Map<Player, Card> maxCardAndPlayer = getLageCardAndPlayer(list);
//            roundPlayerAndCard.putAll(maxCardAndPlayer);
//
//            for (Player player : list) {
//                if (!maxCardAndPlayer.containsKey(player)) {
//                    //组内本轮拿有最大牌的玩家出最大牌，其余玩家出最小牌
//                    roundPlayerAndCard.put(player, player.getHandCards().get(0));
//                }
//            }
//        });
//        return roundPlayerAndCard;
//    }

    /**
     * 本轮算分并从玩家中删除本轮出的牌
     *
     * @param winner
     * @param roundCardMap
     */
    @Override
    public void calculateScoreAndRemoveHandCard(Player winner, Map<Player, Card> roundCardMap) {
        roundCardMap.forEach((player, card) -> {
            //本轮胜者加分
            winner.incScore(card.getPoint());
            //已出的牌回收
            player.removeCard(card);
        });
    }


}
