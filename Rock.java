package com.example;

import java.awt.*;

public class Rock extends object {
    Rock() {
        this.x = (int) (Math.random() * 400);
        this.y = (int) (Math.random() * 300 + 250);
        this.width = 50;
        this.height = 50;
        this.flag = false;
        this.weight = 80;
        this.score = 1;
        this.type = 2;
        this.image = Toolkit.getDefaultToolkit().getImage("F:\\game\\scr\\com\\example\\image\\rock.jpg");
    }

}
