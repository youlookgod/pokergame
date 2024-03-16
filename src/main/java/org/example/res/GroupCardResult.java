package org.example.res;

import org.example.model.Card;

import java.util.List;
import java.util.Map;

/**
 * @Description 牌局结果
 * @Author aiq
 * @Date 2024/3/15 16:30
 * @Version 1.0
 */
public class GroupCardResult extends Result{
    /**
     * 各玩家出牌
     */
    private Map<String, List<Card>> handCardResult;

    /**
     * 各玩家得分
     */
    private Map<String, Integer> playerScore;

    /**
     * 拿出牌顺序及组队情况
     */
    private String sortAndGroup;

    /**
     * 组得分情况及获胜玩家
     */
    private String winner;

    public GroupCardResult(Map<String, List<Card>> handCardResult, Map<String, Integer> playerScore, String sortAndGroup, String winner) {
        this.handCardResult = handCardResult;
        this.playerScore = playerScore;
        this.sortAndGroup = sortAndGroup;
        this.winner = winner;
    }

    public Map<String, List<Card>> getHandCardResult() {
        return handCardResult;
    }

    public void setHandCardResult(Map<String, List<Card>> handCardResult) {
        this.handCardResult = handCardResult;
    }

    public Map<String, Integer> getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(Map<String, Integer> playerScore) {
        this.playerScore = playerScore;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getSortAndGroup() {
        return sortAndGroup;
    }

    public void setSortAndGroup(String sortAndGroup) {
        this.sortAndGroup = sortAndGroup;
    }
}
