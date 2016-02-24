package com.jikexueyuan.anim;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by lixianfeng on 16/2/24.
 */
public class ImageItem {


    private Bitmap bitmap;
    private float x;
    private float y;
    private float width;
    private float height;

    private Random random = new Random();
    private float speedX;
    private float speedY;
    private float stopY;

    private int state;
    public static final int STANDARD = 1;
    public static final int REPEAT = 2;

    public ImageItem(Bitmap bitmap, float width, float height, int state) {
        this.bitmap = bitmap;
        this.height = height;
        this.width = width;
        this.state = state;

        init();
    }

    private void init() {
        //坐标的随机生成
        x = random.nextInt((int) width);
        y = 0;
        //速度的随机生成
        speedX = 3 + random.nextInt(10);
        speedY = 5 + random.nextInt(10);

        if (state == STANDARD) {
            stopY = bitmap.getHeight() + random.nextInt(200);
        }
    }

    protected void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }

    protected void move() {


        if (x > width - bitmap.getWidth()) {
//            x = 0;
            speedX *= -1;
            x = width - bitmap.getWidth();
        } else if (x < 0) {
            speedX *= -1;
            x = 0;
        }
        if (y > height - stopY) {
//            y = 0;
            if (state == REPEAT) {
                init();
            }
            return;
        }
        x += speedX;
        y += speedY;
    }

}
