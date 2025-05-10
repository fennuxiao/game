package com.example;

import java.awt.*;
import java.math.*;

public class line {
    // 起点坐标
    int x = 210;
    int y = 125;
    // 终点坐标
    int endx = 350;
    int endy = 350;
    double lenth = 100;
    double min_length = 100;
    double max_length = 630;
    double n = 0.1;
    // 方向
    int direction = 1;
    // 状态 正常0 抓取1 收回2 3抓取收回
    int state = 0;
    Image image = Toolkit.getDefaultToolkit().getImage("F:\\game\\scr\\com\\example\\image\\hook.jpg");// 钩子
    GameWin frame;

    line(GameWin frame) {
        this.frame = frame;
    }

    void logic() {
        for (object o : this.frame.objects)
            if (endx > o.x && endx < o.x + o.width && endy > o.y
                    && endy < o.y + o.height) {
                state = 3;
                o.flag = true;
            }
    }

    void changdu(Graphics g) {
        endx = x + (int) (lenth * Math.cos(n * Math.PI));
        endy = y + (int) (lenth * Math.sin(n * Math.PI));
        g.setColor(Color.black);
        g.drawLine(x - 1, y, endx - 1, endy);
        g.drawLine(x + 1, y, endx + 1, endy);
        g.drawLine(x, y, endx, endy);
        g.drawImage(image, endx - 15, endy - 2, 30, 30, null);
    }

    void paintSelf(Graphics g) {
        logic();
        switch (state) {
            case 0:
                if (n < 0.1)
                    direction = 1;
                else if (n > 0.9)
                    direction = -1;
                n = n + direction * 0.0025;
                changdu(g);
                break;
            case 1:
                if (lenth < max_length) {
                    lenth += 2;
                    changdu(g);
                } else
                    state = 2;
                break;
            case 2:
                if (lenth > min_length) {
                    lenth -= 2;
                    changdu(g);
                } else
                    state = 0;
                break;
            case 3:
                int m = 1;// 接收石块和金块质量
                if (lenth > min_length) {
                    lenth -= 2;
                    changdu(g);
                    for (object o : this.frame.objects) {
                        if (o.flag) {
                            m += o.weight;
                            o.x = endx - o.width / 2;
                            o.y = endy;
                            if (lenth <= min_length) {
                                o.x = -200;
                                o.y = -200;
                                o.flag = false;
                                bg.water_flag = false;
                                bg.sum_score += o.score;// 积分
                                state = 0;
                            }
                            if (bg.water_flag) {
                                if (o.type == 2) {
                                    o.x = -200;
                                    o.y = -200;
                                    o.flag = false;
                                    bg.water_flag = false;
                                    state = 2;
                                }
                                if (o.type == 1) {
                                    m = 1;
                                }
                            }

                        }

                    }

                }
                try {
                    Thread.sleep(m);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    // 重置线
    void regame() {
        n = 0;
        lenth = 100;
    }
}
