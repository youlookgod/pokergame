package org.example.strategy;

import org.example.model.Card;
import org.example.model.Player;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @Description 出牌策略
 * @Author aiq
 * @Date 2024/3/16 0:08
 * @Version 1.0
 */
public interface Strategy {
    public Map<Player, Card> doStrategy(List<Player> playerList, Comparator<Card> judgeComparator);
}
