package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {
    static int state = 0;// 0未开始 1运行中 2商店 3失败 4胜利
    List<object> objects = new ArrayList<>();// 存储金块,石块
    bg bg0 = new bg();
    line line0 = new line(this);

    {
        boolean isPlace = true;// 是否放置
        for (int i = 0; i < 5; i++) {
            double random = Math.random();
            gold gold_duidiepanduan;

            if (random < 0.33)
                gold_duidiepanduan = new goldmini();
            else if (random < 0.66)
                gold_duidiepanduan = new plusgold();
            else
                gold_duidiepanduan = new gold();

            for (object obj : objects) {
                if (obj.getRect().intersects(gold_duidiepanduan.getRect())) {
                    isPlace = false;// 重合

                }

            }
            if (isPlace) {
                objects.add(gold_duidiepanduan);
            } else {
                isPlace = true;
                i--;
            }
        }
        for (int i = 0; i < 2; i++) {
            boolean isPlace_rock = true;
            Rock rock_duidiepanduan = new Rock();
            for (object obj : objects) {
                if (obj.getRect().intersects(rock_duidiepanduan.getRect())) {
                    isPlace_rock = false;// 重合
                }

            }
            if (isPlace_rock) {
                objects.add(rock_duidiepanduan);
            } else {
                isPlace_rock = true;
                i--;
            }
        }
    }

    Image offScreenImage;

    void launch() {
        this.setVisible(true);
        this.setSize(474, 630);
        this.setLocationRelativeTo(null);
        this.setTitle("202305190556马旭涵");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println(2);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (state) {
                    case 0:
                        if (e.getButton() == 3) {
                            state = 1;
                            bg0.starttime = System.currentTimeMillis();
                        }
                        break;
                    case 1:
                        if (e.getButton() == 1 && line0.state == 0) {
                            line0.state = 1;
                        }

                        if (e.getButton() == 3 && line0.state == 3 && bg.water_num > 0) {
                            bg.water_num--;
                            bg.water_flag = true;
                        }
                        break;
                    case 2:
                        if (e.getButton() == 1) {
                            bg0.shop = true;
                        }
                        if (e.getButton() == 3) {
                            state = 1;
                            bg0.starttime = System.currentTimeMillis();
                        }
                        break;
                    case 3:

                    case 4:
                        if (e.getButton() == 1) {
                            state = 0;
                            bg0.regame();
                            line0.regame();
                        }
                        break;
                    default:
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 不实现就报错,只能把这几个方法添加上来
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        while (true)

        {
            repaint();
            nextLevel();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void nextLevel() {// 下一关
        // System.out.println(1);
        if (state == 1 && bg0.gameTime()) {
            if (bg.sum_score >= bg0.goal) {
                // System.out.println("进入下一关");
                if (bg.level == 5) {
                    state = 4;
                    // bg0.starttime = System.currentTimeMillis();
                } else {
                    state = 2;
                    bg.level++;
                    // bg0.starttime = System.currentTimeMillis();
                }

            } else {
                state = 3;
                // System.out.println("游戏失败");
            }
            dispose();
            GameWin gamewin1 = new GameWin();
            gamewin1.launch();

        }

    }

    @Override
    public void paint(Graphics g) {
        offScreenImage = this.createImage(474, 630);
        Graphics gImage = offScreenImage.getGraphics();

        bg0.paintSelf(gImage);
        if (state == 1) {
            line0.paintSelf(gImage);
            for (object obj : objects) {
                obj.paintSelf(gImage);
            }
            line0.paintSelf(gImage);
        }
        g.drawImage(offScreenImage, 0, 0, null);

    }

    public static void main(String args[]) {
        GameWin gamewin = new GameWin();
        gamewin.launch();

    }

}
