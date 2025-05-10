package com.example;

import java.awt.*;

public class bg {
    static int level = 1;// 关卡数
    int goal = level * 5;// 过关所需分数
    Image bg = Toolkit.getDefaultToolkit().getImage("F:\\game\\scr\\com\\example\\image\\bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("F:\\game\\scr\\com\\example\\image\\bg2.jpg");
    Image people = Toolkit.getDefaultToolkit().getImage("F:\\game\\scr\\com\\example\\image\\people.jpg");
    Image water = Toolkit.getDefaultToolkit().getImage("F:\\game\\scr\\com\\example\\image\\yaoshui.jpg");
    static int sum_score = 0;
    static int water_num = 3;
    static boolean water_flag = false;
    long starttime;
    long endtime;
    int price = 5;
    boolean shop;// 是否购买,f不购买

    boolean gameTime() {// 判断游戏时间是否结束
        long time = (endtime - starttime) / 1000;
        if (time > 20) {
            System.out.println("游戏时间结束");
            return true;
        }
        return false;
    }

    // 重置元素
    void regame() {
        level = 1;
        goal = level * 5;
        sum_score = 0;
        water_num = 3;
        water_flag = false;
    }

    public static void drawString(Graphics g, int size, Color color, String str, int x, int y) { // 绘制字符串
        g.setColor(color);
        g.setFont(new Font("宋体", Font.BOLD, size));
        g.drawString(str, x, y);
    }

    // 我找到的人物图片过大，所以我将其缩小绘制
    public void paintSelf(Graphics g) {// 绘制字符串
        g.drawImage(bg, 0, 125, null);
        g.drawImage(bg1, 0, 0, null);
        switch (GameWin.state) {
            case 0:
                drawString(g, 80, Color.red, "准备游戏", 90, 250);
                break;
            case 1:
                g.drawImage(people, 170, 25, 100, 100, null);
                drawString(g, 30, Color.pink, "分数：" + sum_score, 10, 110);
                g.drawImage(water, 420, 20, 50, 50, null);
                drawString(g, 30, Color.pink, "*" + water_num, 420, 95);
                // 关卡数
                drawString(g, 20, Color.red, "第" + level + "关", 10, 50);

                // 目标积分
                drawString(g, 30, Color.red, "目标:" + goal, 10, 80);
                // 时间
                endtime = System.currentTimeMillis();
                long time = 20 - (endtime - starttime) / 1000;
                System.out.println("时间:" + time);
                drawString(g, 15, Color.red, "时间:" + (time > 0 ? time : 0), 400, 115);
                break;
            case 2:
                g.drawImage(water, 200, 150, 50, 50, null);
                drawString(g, 30, Color.red, "价格:" + price, 90, 200);
                drawString(g, 30, Color.red, "是否购买?", 90, 250);
                if (shop) {
                    sum_score -= price;
                    water_num++;
                    shop=false;
                    GameWin.state=1;
                    starttime = System.currentTimeMillis();
                }
                break;
            case 3:
                drawString(g, 80, Color.red, "游戏失败", 90, 250);
                drawString(g, 80, Color.pink, "分数：" + sum_score, 90, 150);
                break;
            case 4:
                drawString(g, 80, Color.red, "恭喜过关", 90, 250);
                drawString(g, 80, Color.pink, "分数：" + sum_score, 90, 150);
                break;

        }

    }

}
