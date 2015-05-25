package com.example.denis.brick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.Random;


public class TovarnaOpek {
    private ArrayList<Opeka> opeke;
    private int steviloOpek = 0;
    private int tocke = 0;

    public TovarnaOpek(Context context) {
        opeke = new ArrayList<Opeka>();
        Random rand = new Random();
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int potrebnihZadetkov = rand.nextInt(3) + 1;
                opeke.add(new Opeka(i * 70, j * 60, potrebnihZadetkov, context));
                steviloOpek += potrebnihZadetkov;
            }
        }
    }

    public void draw(Canvas canvas) {
        //izris vseh opek
        for (Opeka b : opeke) {
            b.draw(canvas);
        }
    }

    public void checkCollision(Zoga zoga) {
        for (Opeka opeka : opeke) {
            if (opeka.potrebnihZadetkov > 0 && CollisionTest(zoga.mejeRobov, opeka.mejeRobov)) {
                if (zoga.mejeRobov.bottom >= opeka.mejeRobov.top && zoga.y < opeka.mejeRobov.top) {
                    zoga.hitrostY = -zoga.hitrostY;
                    zoga.y = opeka.mejeRobov.top - zoga.velikost;
                } else if (zoga.mejeRobov.top <= opeka.mejeRobov.bottom && zoga.y > opeka.mejeRobov.bottom) {
                    zoga.hitrostY = -zoga.hitrostY;
                    zoga.y = opeka.mejeRobov.bottom + zoga.velikost;
                } else if (zoga.mejeRobov.left <= opeka.mejeRobov.right && zoga.x > opeka.mejeRobov.right) {
                    zoga.hitrostX = -zoga.hitrostX;
                    zoga.x = opeka.mejeRobov.right + zoga.velikost;
                } else if (zoga.mejeRobov.right >= opeka.mejeRobov.left && zoga.x < opeka.mejeRobov.left) {
                    zoga.hitrostX = -zoga.hitrostX;
                    zoga.x = opeka.mejeRobov.left - zoga.velikost;
                }
                opeka.potrebnihZadetkov--;
                steviloOpek--;
                tocke += 5;
            }
        }
    }

    public boolean CollisionTest(RectF robovi1, RectF robovi2) {
        return robovi1.left <= robovi2.right && robovi1.right >= robovi2.left && robovi1.top <= robovi2.bottom && robovi1.bottom >= robovi2.top;
    }

    public boolean aliObstajajoOpeke() {
        return steviloOpek > 0;
    }

    public int getTocke() {
        return tocke;
    }
}
