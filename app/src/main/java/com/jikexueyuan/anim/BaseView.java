package com.jikexueyuan.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public abstract class BaseView extends View {
    private LogicThread logicThread;
    protected long sleepTime = 30;
    private boolean running = true;

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context) {
        super(context);
    }

    @Override
    protected final void onDraw(Canvas canvas) {

        if (logicThread == null) {
            logicThread = new LogicThread();
            logicThread.start();
        } else {
            drawSub(canvas);
        }

    }

    @Override
    protected void onDetachedFromWindow() {
        running = false;
        super.onDetachedFromWindow();
    }

    class LogicThread extends Thread {

        @Override
        public void run() {
            init();
            long workTime;
            while (running) {
                workTime = System.currentTimeMillis();
                logic();
                workTime = System.currentTimeMillis() - workTime;
                try {
                    if (workTime < sleepTime) {
                        Thread.sleep(sleepTime - workTime);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postInvalidate();
            }
        }
    }

    protected abstract void init();
    protected abstract void drawSub(Canvas canvas);
    protected abstract void logic();

}
