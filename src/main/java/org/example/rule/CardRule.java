package org.example.rule;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 纸牌规则
 * @Author aiq
 * @Date 2024/3/15 15:24
 * @Version 1.0
 */
public class CardRule extends Rule implements Serializable {
    private static final long serialVersionUID = -1202571922501972625L;

    /**
     * 牌面开始值
     */
    private int numStart;

    /**
     * 牌面结束值
     */
    private int numEnd;

    /**
     * 是否需要大小王
     */
    private boolean needKing;

    /**
     * 纸牌花色
     */
    private List<String> colors;

    public CardRule() {
    }

    public CardRule(int numStart, int numEnd, boolean needKing, List<String> colors) {
        this.numStart = numStart;
        this.numEnd = numEnd;
        this.needKing = needKing;
        this.colors = colors;
    }

    public int getNumStart() {
        return numStart;
    }

    public void setNumStart(int numStart) {
        this.numStart = numStart;
    }

    public int getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(int numEnd) {
        this.numEnd = numEnd;
    }

    public boolean isNeedKing() {
        return needKing;
    }

    public void setNeedKing(boolean needKing) {
        this.needKing = needKing;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
