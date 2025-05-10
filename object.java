package com.example;

import java.awt.*;

public class object {
    // 坐标宽高
    int x;
    int y;
    int width;
    int height;
    // 图片
    Image image;
    // 标记,是否能移动
    boolean flag;
    // 质量
    int weight;
    // 积分
    int score;
    // 标记,区分金块1,石块2
    int type;

    // 绘制方法
    public void paintSelf(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
