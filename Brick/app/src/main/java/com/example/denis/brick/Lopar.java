package com.example.denis.brick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Lopar {
    float velikostX = 100;
    float velikostY = 20;
    float oldX;
    float oldY;
    float x;             // Center (x,y)
    float y;
    RectF mejeRobov;
    Bitmap sprite;

    public Lopar(float x, float y, Context context) {
        mejeRobov = new RectF();
        this.x = velikostX + x;
        this.y = velikostY + y;
        mejeRobov.set(this.x - velikostX, this.y - velikostY, this.x + velikostX, this.y + velikostY);
        sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.paddle);
        sprite = Bitmap.createScaledBitmap(sprite, (int) velikostX * 2, (int) velikostY * 2, true);
    }


    public void draw(Canvas canvas) {
        //izris loparja
        canvas.drawBitmap(sprite, mejeRobov.left, mejeRobov.top, null);
    }

    public void moveX(float x) {
        this.oldX = x;
        this.x = x;
        mejeRobov.set(this.x - velikostX, this.y - velikostY, this.x + velikostX, this.y + velikostY);
    }
}
