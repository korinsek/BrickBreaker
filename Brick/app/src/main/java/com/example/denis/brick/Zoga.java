package com.example.denis.brick;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Zoga {
    float velikost = 15; //velikost zogice
    float x;             // Center (x,y)
    float y;
    float hitrostX;
    float hitrostY;
    RectF mejeRobov;
    Boolean dovoliIzris;
    Bitmap zoga;
    boolean izvenRoba;


    public Zoga(float x, float y, float hitrostX, float hitrostY, Context context) {
        mejeRobov = new RectF();
        this.x = velikost + x;
        this.y = velikost + y;
        this.hitrostX = hitrostX;
        this.hitrostY = hitrostY;
        mejeRobov.set(this.x - velikost, this.y - velikost, this.x + velikost, this.y + velikost);
        dovoliIzris = true;
        zoga = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball1);
        zoga = Bitmap.createScaledBitmap(zoga, (int) velikost * 2, (int) velikost * 2, true);
        izvenRoba = false;
    }

    public void Move() {
        y += hitrostY;
        x += hitrostX;
        mejeRobov.set(x - velikost, y - velikost, x + velikost, y + velikost);
    }

    public void checkCollision(int xMax, int xMin, int yMax, int yMin, Lopar lopar) {
        //preveri dotik z robovi
        if (x + velikost > xMax) {
            hitrostX = -hitrostX;
            x = xMax - velikost;
        } else if (x - velikost < xMin) {
            hitrostX = -hitrostX;
            x = xMin + velikost;
        }
        if (y + velikost > yMax) {
            hitrostY = -hitrostY;
            y = yMax - velikost;
            //presežemo spodnji rob ekrana torej zogica je izven roba in je konec igre
            izvenRoba = true;
        } else if (y - velikost < yMin) {
            hitrostY = -hitrostY;
            y = yMin + velikost;
        }

        trkZogaLopar(lopar);
    }

    private void trkZogaLopar(Lopar lopar) {
        //preveri dotik z loparjem
        if (lopar != null) {
            if (CollisionTest(mejeRobov, lopar.mejeRobov)) {

                if (mejeRobov.bottom >= lopar.mejeRobov.top && this.y < lopar.mejeRobov.top) {
                    float razdalja = this.x - lopar.x;

                    hitrostX = (((razdalja / ((lopar.mejeRobov.right - lopar.mejeRobov.left) / 2)) * 3.0f) + hitrostX) / 2;
                    hitrostY = -hitrostY;
                    y = lopar.mejeRobov.top - velikost;

                } else if (mejeRobov.top <= lopar.mejeRobov.bottom && this.y > lopar.mejeRobov.bottom) {
                    hitrostY = -hitrostY;
                    y = lopar.mejeRobov.bottom + velikost;
                } else if (mejeRobov.left <= lopar.mejeRobov.right && this.x > lopar.mejeRobov.right) {
                    hitrostX = -hitrostX;
                    x = lopar.mejeRobov.right + velikost;
                } else if (mejeRobov.right >= lopar.mejeRobov.left && this.x < lopar.mejeRobov.left) {
                    hitrostX = -hitrostX;
                    x = lopar.mejeRobov.left - velikost;
                }
            }
        }
    }

    public boolean CollisionTest(RectF one, RectF two) {
        return one.left <= two.right && one.right >= two.left && one.top <= two.bottom && one.bottom >= two.top;
    }


    public void draw(Canvas canvas) {
        //izris zogice
        if (dovoliIzris) {
            canvas.drawBitmap(zoga, mejeRobov.left, mejeRobov.top, null);
        }
    }


}
