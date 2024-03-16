package org.example.strategy;

import org.example.model.Card;
import org.example.model.Player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 组队利益最大化出牌策略
 * @Author aiq
 * @Date 2024/3/15 23:58
 * @Version 1.0
 */
public class GroupFirstStrategy implements Strategy {
    private static GroupFirstStrategy groupFirstStrategy;

    private GroupFirstStrategy() {
    }

    public static synchronized GroupFirstStrategy getInstance() {
        if (null == groupFirstStrategy) {
            groupFirstStrategy = new GroupFirstStrategy();
        }
        return groupFirstStrategy;
    }

    public Map<Player, Card> doStrategy(List<Player> playerList, Comparator<Card> judgeComparator) {
        for (Player player : playerList) {
            //从小到大排列
            player.getHandCards().sort(judgeComparator);
        }

        Map<Integer, List<Player>> groupPlayerMap = playerList.stream().collect(Collectors.groupingBy(Player::getGroup));

        Map<Player, Card> roundPlayerAndCard = new HashMap<>();
        groupPlayerMap.forEach((g, list) -> {

            //获取当前组持有最大牌的玩家
            Map<Player, Card> maxCardAndPlayer = getLageCardAndPlayer(list, judgeComparator);
            roundPlayerAndCard.putAll(maxCardAndPlayer);

            for (Player player : list) {
                if (!maxCardAndPlayer.containsKey(player)) {
                    //组内本轮拿有最大牌的玩家出最大牌，其余玩家出最小牌
                    roundPlayerAndCard.put(player, player.getHandCards().get(0));
                }
            }
        });
        return roundPlayerAndCard;
    }

    /**
     * 找出组内最大牌及持有者
     * 可以由子类覆盖
     *
     * @param playerList
     * @return
     */
    public Map<Player, Card> getLageCardAndPlayer(List<Player> playerList, Comparator<Card> judgeComparator) {
        Card maxCard = null;
        Player maxCardPlayer = null;
        for (Player player : playerList) {
            List<Card> cards = player.getHandCards();
            Card lastCard = cards.get(cards.size() - 1);

            if (null == maxCard) {
                //取最大的牌
                maxCard = lastCard;
                maxCardPlayer = player;
            } else {
                int compare = judgeComparator.compare(lastCard, maxCard);
                if (compare > 0) {
                    maxCard = lastCard;
                    maxCardPlayer = player;
                }
            }
        }
        Map<Player, Card> roundGroupMaxCard = new HashMap<>();
        roundGroupMaxCard.put(maxCardPlayer, maxCard);
        return roundGroupMaxCard;
    }

}
