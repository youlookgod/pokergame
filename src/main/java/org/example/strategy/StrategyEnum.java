package org.example.strategy;

/**
 * @Description 出牌策略枚举
 * @Author aiq
 * @Date 2024/3/15 21:00
 * @Version 1.0
 */
public enum StrategyEnum {
    RANDOM("random", "随机"),
    TIAN_JI_SAI_MA("tian_ji", "田忌赛马"),

    YOU_FIRST("you_first", "让你先出"),

    GROUP_WIN("group_win", "组队利益最大化"),
    ;

    StrategyEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 出牌策略编号
     */
    private String code;

    /**
     * 出牌策略描述
     */
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
