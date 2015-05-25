package com.example.denis.brick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Opeka {
    float velikostX = 30;
    float velikostY = 20;
    float x;             // Center (x,y)
    float y;
    int potrebnihZadetkov;
    RectF mejeRobov;
    Bitmap sprite1;
    Bitmap sprite2;
    Bitmap sprite3;

    public Opeka(float x, float y, int potrebnihZadetkov, Context context) {
        mejeRobov = new RectF();
        this.x = velikostX + x;
        this.y = velikostY + y;
        mejeRobov.set(this.x - velikostX, this.y - velikostY, this.x + velikostX, this.y + velikostY);
        this.potrebnihZadetkov = potrebnihZadetkov;

        //različne slikice za različne opeke
        sprite1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick1);
        sprite1 = Bitmap.createScaledBitmap(sprite1, (int) velikostX * 2, (int) velikostY * 2, true);
        sprite2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick2);
        sprite2 = Bitmap.createScaledBitmap(sprite2, (int) velikostX * 2, (int) velikostY * 2, true);
        sprite3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick3);
        sprite3 = Bitmap.createScaledBitmap(sprite3, (int) velikostX * 2, (int) velikostY * 2, true);

    }

    public void draw(Canvas canvas) {
        //ce je dovoljen izris, narisi sliko opeke
        if (potrebnihZadetkov == 1) {
            canvas.drawBitmap(sprite1, mejeRobov.left, mejeRobov.top, null);
        } else if (potrebnihZadetkov == 2) {
            canvas.drawBitmap(sprite2, mejeRobov.left, mejeRobov.top, null);
        } else if (potrebnihZadetkov == 3) {
            canvas.drawBitmap(sprite3, mejeRobov.left, mejeRobov.top, null);
        }
    }
}
