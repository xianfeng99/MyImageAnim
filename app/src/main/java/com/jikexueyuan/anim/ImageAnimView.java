package com.jikexueyuan.anim;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lixianfeng on 16/2/24.
 */
public class ImageAnimView extends BaseView {

//    private ImageItem imageItem;
    private ArrayList<ImageItem> list = new ArrayList<>();

    private int size = 8;
    private String imagePath;
    private int state;

    public ImageAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ImageAnimView);

        size = ta.getInteger(R.styleable.ImageAnimView_itemSize, 0);
        imagePath = ta.getString(R.styleable.ImageAnimView_imagePath);
        state = ta.getInteger(R.styleable.ImageAnimView_itemType, ImageItem.REPEAT);

        ta.recycle();

    }

    public ImageAnimView(Context context) {
        super(context);
    }

    @Override
    protected void init() {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getResources().getAssets().open(imagePath));
//            imageItem = new ImageItem(bitmap, getWidth(), getHeight());
            for(int i = 0; i < size; i ++){
                list.add(new ImageItem(bitmap, getWidth(), getHeight(), state));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void drawSub(Canvas canvas) {
//        imageItem.draw(canvas);
        for(ImageItem item : list){
            item.draw(canvas);
        }
    }

    @Override
    protected void logic() {
//        imageItem.move();
        for(ImageItem item : list){
            item.move();
        }
    }
}
