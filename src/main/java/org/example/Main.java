package org.example;

import org.example.comparator.CardComparator;
import org.example.manager.CardManager;
import org.example.manager.PlayerManager;
import org.example.judge.GroupPokerJudge;
import org.example.res.GroupCardResult;
import org.example.rule.CardRule;
import org.example.strategy.StrategyEnum;
import org.example.util.Joiner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        //玩家列表
        List<String> player = Arrays.asList("甲", "乙", "丙", "丁");
        //纸牌花色
        List<String> cardColor = Arrays.asList("A", "B", "C", "D");

        //纸牌管理器
        CardManager cardManager = new CardManager();
        //玩家管理器
        PlayerManager playerManager = new PlayerManager(StrategyEnum.GROUP_WIN);
        //牌型规则
        CardRule cardRule = new CardRule(1, 13, true, cardColor);
        CardComparator cardComparator = new CardComparator(cardRule);

        GroupPokerJudge groupPokerJudge = new GroupPokerJudge(cardManager, playerManager, cardComparator);

        //开始玩牌
        GroupCardResult result = groupPokerJudge.start(cardRule, player);

        //出牌
        System.out.println("出牌");
        System.out.println(result.getSortAndGroup());
        result.getHandCardResult().forEach((name, cards) -> {
            System.out.println(name + "  " + Joiner.on(",").join(cards.stream().map(s -> s.getColor() + s.getCardNo()).collect(Collectors.toList())));
        });

        //得分
        System.out.println("得分");
        result.getPlayerScore().forEach((name, score) -> {
            System.out.println(name + "  " + score);
        });

        //赢家
        System.out.println(result.getWinner());


        Long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start) + "ms");
    }
}