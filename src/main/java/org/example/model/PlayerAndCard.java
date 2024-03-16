package org.example.model;

import java.io.Serializable;

/**
 * @Description 玩家出牌对象
 * @Author aiq
 * @Date 2024/3/15 17:43
 * @Version 1.0
 */
public class PlayerAndCard implements Serializable {

    private static final long serialVersionUID = -6414266098119312769L;

    private String name;

    private Card card;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
