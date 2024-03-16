扑克牌对弈 编程考试试题
题目：
4 个人甲乙丙丁在一起玩一副扑克纸牌，每张纸牌按照数字计分，不同花色的相同数字的纸牌分值一样。
其中 1-10 各种花色的纸牌分别为 1-10，J、Q、K 分别被记为 11,12,13，大王和小王分别记为 20。四种花
色（方片，梅花、红桃、黑桃）分别被记为 A、B、C、D。如红桃 K 表示为 13C，分值为 13；方片 5 表示
为 5A，分值为 5。大王表示为 20K，分值为 20，小王表示为 20Q，分值为 20，54 张纸牌叠在一起倒扣在
桌子上。

游戏过程如下：
1. 洗牌：54 张纸牌随机顺序组合。
2. 4 人排序：随机产生 4 人的拿牌顺序，出牌顺序与拿牌顺序相同。第一个人拿牌，即第一个人出牌。
3. 4 人排序后，两两组队，按照顺序第 1 人和第 3 人组成 1 队，第 2 人和第 4 人组成 1 对。
4. 分牌：按拿牌顺序每人轮流拿牌，每人拿 13 张纸牌，最后两张纸牌留在桌面。
5. 信息：每个人不知道其他人的牌，也不知道留在桌面上的牌，可以获得的信息包括自己持有的牌和 4
   个人出过的牌。队内的成员可以在每一轮沟通出牌的策略。
6. 出牌：从第一个拿牌的人开始出牌（可以选择最大获胜机会的策略），每一轮 4 人出牌，然后比大
   小，拥有牌最大的人获取当前轮次的 4 张牌。出过的牌后面不可以再出。大小判定规则，纸牌分值
   (大) > 纸牌分值(小)，相同纸牌分值，按照黑桃>红桃>梅花>方片的顺序判定大小。例如黑桃 9 > 红
   桃 9。有大王或小王的牌在比较大小时，大小判定的规则是 大王>小王>黑桃 13>红桃 13>梅花 13>
   方片 13>黑桃 12……。举例：第 1 轮，甲出 3C，乙出 9D，丙出 6C，丁出 13B，则丁赢取 4 张牌
   （3C，9D，6C，13B）。
7. 判定胜负：每个人手中的牌的分值总和为每个人的得分，每队的得分为两个人得分的和，得分最大的
   队伍者获胜，如果两队得分相同，则平局。
   
8. 编程要求：
1. 使用面向对象的方法进行编程，可以根据自己偏好自由选择编程语言。
2. 代码实现要考虑到扩展性。要在牌的分值，大小比较规则发生变化时，代码能够很容易地扩展和支
   持。
3. 输出结果:
   每人出牌结果
   每人得分
   获胜选手
   输出示例参考如下：
   出牌
   拿/出牌顺序：甲丙丁乙。组队，1 号队：甲，丁，2 号队：丙，乙
   甲 3C，6B，8B，11D，7C，7D，2C，8D，5B，12B，12C，4B，10C
   丙 6C，1B，4D，2A，10B，13A，2D，5A，11B，20Q，11A，4C，8C
   丁 13B，4A，5C，1A，6A，10A，11C，7A，12A，7B，3B，13D，3D
   乙 9D，10D，3A，9C，8A，9A，6D，20K，12D，1D，9B，2B，13C
   得分
   甲 78
   丙 110
   丁 75
   乙 135
   1 号队得分：153，2 号队得分 245
   得分最多：2 号队：丙，乙
   
4. 其他要求：
1. 提交物包括：完整项目源程序和已经编译好的可执行程序，并分别按照\src, \bin 三个目录分别存放
   好，打包提交。
2. 可以网上查询相关技术资料，但要确保独立完成，不得互相抄袭代码。
   评分要点及检查项:
1. 合理正确的使用面向对象的方法进行程序结构设计。
2. 类设计合理，数据结构运用合理，扩展性良好。
3. 编码规范，函数及类命名合理准确，无歧义。
4. 程序的关键实现部分要进行逻辑注释说明。
5. 程序源码可以编译通过，无错误及警告提示，无内存等资源泄露。
6. 请同学做好系统分析，准确理解题意，正确模拟运行结果