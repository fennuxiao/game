package com.example;

import java.awt.*;

public class gold extends object {
    gold() {
        this.x = (int) (Math.random() * 400);
        this.y = (int) (Math.random() * 300 + 250);
        this.width = 50;
        this.height = 50;
        this.flag = false;
        this.weight = 40;
        this.score = 4;
        this.type = 1;
        this.image = Toolkit.getDefaultToolkit().getImage("F:\\game\\scr\\com\\example\\image\\jinzi.jpg");
    }
}

class goldmini extends gold {
    goldmini() {
        this.width = 35;
        this.height = 35;
        this.weight = 20;
        this.score = 2;
    }
}

class plusgold extends gold {
    plusgold() {
        this.x = (int) (Math.random() * 375);
        this.width = 75;
        this.height = 75;
        this.weight = 90;
        this.score = 8;
    }
}