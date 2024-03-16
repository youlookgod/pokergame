package org.example.manager;

import org.example.model.Card;
import org.example.model.Player;
import org.example.strategy.GroupFirstStrategy;
import org.example.strategy.Strategy;
import org.example.strategy.StrategyEnum;

import java.util.*;

/**
 * @Description 玩家管理器
 * @Author aiq
 * @Date 2024/3/15 14:09
 * @Version 1.0
 */
public class PlayerManager {
    /**
     * 玩牌策略
     */
    private StrategyEnum strategyEnum;

    /**
     * 玩家列表
     */
    private List<Player> playerList;

    public PlayerManager(StrategyEnum strategyEnum) {
        Objects.requireNonNull(strategyEnum);
        this.strategyEnum = strategyEnum;
    }

    /**
     * 初始化玩家
     *
     * @param playerNameList
     * @return
     */
    public List<Player> initPlayers(List<String> playerNameList) {
        List<Player> players = new ArrayList<>();
        for (String name : playerNameList) {
            players.add(new Player(name));
        }

        //玩家排序
        sort(players);

        //玩家分组
        group(players);
        this.playerList = players;
        return players;
    }

    /**
     * 玩家排序
     *
     * @param playerList
     */
    public void sort(List<Player> playerList) {
        Collections.shuffle(playerList);
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).setOrder(i + 1);
        }

        Collections.sort(playerList);
    }

    /**
     * 玩家组队
     */
    public void group(List<Player> playerList) {
        int index = 0;
        for (Player player : playerList) {
            if (index % 2 == 0) {
                player.setGroup(1);
            } else {
                player.setGroup(2);
            }
            index++;
        }
    }

    /**
     * 出牌
     */
    public Map<Player, Card> playCard(int round, Comparator<Card> judgeComparator) {
        Strategy strategy = getStrategy();
        return strategy.doStrategy(playerList,judgeComparator);
    }

    public Strategy getStrategy() {
        Strategy strategy = null;
        switch (strategyEnum) {
            case GROUP_WIN:
                strategy = GroupFirstStrategy.getInstance();
                break;
            default:
                strategy = GroupFirstStrategy.getInstance();

        }
        return strategy;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
