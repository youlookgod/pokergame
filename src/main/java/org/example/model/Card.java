package org.example.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description 纸牌对象
 * @Author aiq
 * @Date 2024/3/15 14:22
 * @Version 1.0
 */
public class Card implements Serializable {
    private static final long serialVersionUID = -8897878022965537522L;

    /**
     * 花色
     */
    private String color;

    /**
     * 牌号
     */
    private String cardNo;

    /**
     * 分值
     */
    private Integer point;

    public Card() {
    }

    public Card(String color, String cardNo, Integer point) {
        this.color = color;
        this.cardNo = cardNo;
        this.point = point;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
