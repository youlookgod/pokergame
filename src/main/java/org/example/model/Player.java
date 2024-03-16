package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description 玩家对象
 * @Author aiq
 * @Date 2024/3/15 14:49
 * @Version 1.0
 */
public class Player implements Comparable<Player>, Serializable {
    private static final long serialVersionUID = -506911068630710696L;

    /**
     * 玩家序号
     */
    private Integer order;

    /**
     * 玩家名称
     */
    private String name;

    /**
     * 玩家得分
     */
    private int score;

    /**
     * 分组
     */
    private int group;

    /**
     * 玩家手牌
     */
    private List<Card> handCards;

    /**
     * 玩家出牌
     */
    private List<Card> playCards;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.order = 0;
        this.group = 0;
        this.handCards = new ArrayList<>();
        this.playCards = new ArrayList<>();
    }

    public void incScore(int sco){
        score += sco;
    }
    public void removeCard(Card card){
        handCards.remove(card);
    }

    public void addToHand(Card card) {
        handCards.add(card);
    }

    public void addToPlayCard(Card card){
        playCards.add(card);
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public List<Card> getPlayCards() {
        return playCards;
    }

    public void setPlayCards(List<Card> playCards) {
        this.playCards = playCards;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return group == player.group && Objects.equals(order, player.order) && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, name, group);
    }

    @Override
    public int compareTo(Player o) {
        return this.order - o.order;
    }

}
