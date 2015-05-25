package com.example.denis.brick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class ZankaIgre extends View {
    int width;
    int height;
    Bitmap ozadje;
    private int xMin = 0;
    private int xMax;
    private int yMin = 0;
    private int yMax;
    private Paint paint;
    private Zoga zoga;
    private Lopar lopar;
    private TovarnaOpek tovarnaOpek;

    public ZankaIgre(Context context) {
        super(context);

        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        paint = new Paint();

        zoga = new Zoga(width / 2, height / 1.5f, 0, -3, context);

        lopar = new Lopar(width / 3, height - height / 4, context);
        tovarnaOpek = new TovarnaOpek(context);

        //ozadje stiskanje slike
        ozadje = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        ozadje = Bitmap.createScaledBitmap(ozadje, width, height, true);

        this.setFocusable(true);
        this.requestFocus();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //izris ozadja
        canvas.drawBitmap(ozadje, 0, 0, null);
        //izrisi like, tekste in objekte
        zoga.draw(canvas);
        lopar.draw(canvas);
        tovarnaOpek.draw(canvas);

        //izpis točk
        paint.setColor(Color.BLUE);
        paint.setTextSize(20);
        canvas.drawText("Točke:" + tovarnaOpek.getTocke(), width / 2 - 30, 25, paint);
        if (tovarnaOpek.aliObstajajoOpeke() && !zoga.izvenRoba) {
            update();
            invalidate();
        } else {
            paint.setColor(Color.RED);
            paint.setTextSize(40);
            canvas.drawText("Konec igre!", width / 2 - 60, height / 2, paint);
        }
    }

    private void update() {
        //premakni objekt, preveri trke
        zoga.Move();
        // trki z stranicami
        zoga.checkCollision(xMax, xMin, yMax, yMin, lopar);
        tovarnaOpek.checkCollision(zoga);
    }

    // Ce se spremeni velikost
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        //nastavimo novo veliksot ekrana
        xMax = w - 1;
        yMax = h - 1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        lopar.moveX(x);
        return true;
    }
}