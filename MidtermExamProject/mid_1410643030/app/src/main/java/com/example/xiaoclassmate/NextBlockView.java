package com.example.xiaoclassmate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

// NextBlockView.java 遊戲主介面的縮減版，顯示下一個要出現的方塊

@SuppressLint("DrawAllocation")
public class NextBlockView extends View {
    // 網格狀的開始座標值(x,y)
    public static final int beginPoint = 10;
    // 方塊的最大座標值
    private static int max_x, max_y;
    private List<BlockUnit> blockUnits = new ArrayList<BlockUnit>();
    // 背景牆的畫筆
    private static Paint paintWall = null;
    private static final int BOUND_WIDTH_OF_WALL = 2;
    private static Paint paintBlock = null;
    private int div_x = 0;
    // 方塊的顏色種類
    private static final int color[] ={ Color.parseColor("#FF6600"), Color.BLUE, Color.RED, Color.GREEN, Color.GRAY, Color.YELLOW };

    public NextBlockView(Context context) {
        this(context, null);
    }

    public NextBlockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化背景牆的畫筆
        if (paintWall == null) {
            paintWall = new Paint();
            paintWall.setColor(Color.LTGRAY);
            paintWall.setStyle(Paint.Style.STROKE);
            paintWall.setStrokeWidth(BOUND_WIDTH_OF_WALL + 1);
        }
        // 初始化背景牆的畫筆
        if (paintBlock == null) {
            paintBlock = new Paint();
            paintBlock.setColor(Color.parseColor("#FF6600"));
        }
    }

    public void setBlockUnits(List<BlockUnit> blockUnits, int div_x) {
        this.blockUnits = blockUnits;
        this.div_x = div_x;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        max_x = getWidth();
        max_y = getHeight();
        RectF rel;
        // 繪製網格
        int len = blockUnits.size();
        // 繪製方塊
        // Toast.makeText(context, "" + len, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < len; i++) {
            paintBlock.setColor(color[blockUnits.get(i).color]);
            int x = blockUnits.get(i).x - div_x + BlockUnit.UNIT_SIZE * 2;
            int y = blockUnits.get(i).y + BlockUnit.UNIT_SIZE * 2;
            rel = new RectF(x + BOUND_WIDTH_OF_WALL, y + BOUND_WIDTH_OF_WALL,
                    x + BlockUnit.UNIT_SIZE - BOUND_WIDTH_OF_WALL, y + BlockUnit.UNIT_SIZE - BOUND_WIDTH_OF_WALL);
            canvas.drawRoundRect(rel, 8, 8, paintBlock);
            rel = new RectF(x, y, x + BlockUnit.UNIT_SIZE, y + BlockUnit.UNIT_SIZE);
            canvas.drawRoundRect(rel, 8, 8, paintWall);
        }
    }

}