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
import java.util.Arrays;
import java.util.List;

// TetrisViewAW.java 背景牆和方塊都在這裡

@SuppressLint("DrawAllocation")
public class TetrisView extends View {
    // 網格狀的開始座標值(x,y)
    public static final int beginPoint = 10;
    // 方塊的最大座標值
    private static int max_x, max_y;
    // 幾行幾列
    private static int num_x = 0, num_y = 0;
    // 背景牆的畫筆
    private static Paint paintWall = null;
    // 單元方塊的畫筆
    private static Paint paintBlock = null;
    private static final int BOUND_WIDTH_OF_WALL = 2;
    // 目前正在降落的方塊
    private List<BlockUnit> blockUnits = new ArrayList<BlockUnit>();
    // 下一個要顯示的方塊
    private List<BlockUnit> blockUnitBufs = new ArrayList<BlockUnit>();
    private List<BlockUnit> routeBlockUnitBufs = new ArrayList<BlockUnit>();
    // 全部的單元方塊
    private List<BlockUnit> allBlockUnits = new ArrayList<BlockUnit>();
    // 調用這個對象的 Activity
    private TetrisActivity father = null;
    // 保存每行網格中包含單元方塊的個數
    private int[] map = new int[100];
    // 遊戲的執行緒
    private Thread mainThread = null;
    // 遊戲的狀態是開始 or 結束
    private boolean gameStatus = false;
    // 遊戲的狀態是繼續 or 暫停
    private boolean runningStatus = false;
    // 方塊的顏色種類
    private static final int color[] = { Color.parseColor("#FF6600"), Color.BLUE, Color.RED, Color.GREEN, Color.GRAY, Color.YELLOW };
    // 單元方塊的方塊中心的座標
    private int xx, yy;
    // 隨機取得的方塊
    private TetrisBlock tetrisBlock;
    // 分數
    private int score = 0;
    // 目前方塊的種類
    private int blockType = 0;

    public TetrisView(Context context) {
        this(context, null);
    }

    public TetrisView(Context context, AttributeSet attrs) {
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
        tetrisBlock = new TetrisBlock();
        routeBlockUnitBufs = tetrisBlock.getUnits(beginPoint, beginPoint);
        // 每行網格中包含單元方塊的個數全部初始化為 0
        Arrays.fill(map, 0);
    }


    // 設定當前遊戲頁面的父類 Activity
    public void setFather(TetrisActivity tetrisActivity) {
        father = tetrisActivity;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        max_x = getWidth();
        max_y = getHeight();
        // 繪製網格
        RectF rel;
        num_x = 0;
        num_y = 0;
        for (int i = beginPoint; i < max_x - BlockUnit.UNIT_SIZE; i += BlockUnit.UNIT_SIZE) {
            for (int j = beginPoint; j < max_y - BlockUnit.UNIT_SIZE; j += BlockUnit.UNIT_SIZE) {
                rel = new RectF(i, j, i + BlockUnit.UNIT_SIZE, j + BlockUnit.UNIT_SIZE);
                canvas.drawRoundRect(rel, 8, 8, paintWall);
                num_y++;
            }
            num_x++;
        }
        // 隨機產生方塊
        int len = blockUnits.size();
        // 绘制方块
        // Toast.makeText(context, "" + len, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < len; i++) {
            int x = blockUnits.get(i).x;
            int y = blockUnits.get(i).y;
            // 設定目前方塊的顏色
            paintBlock.setColor(color[blockUnits.get(i).color]);
            rel = new RectF(x + BOUND_WIDTH_OF_WALL, y + BOUND_WIDTH_OF_WALL,
                    x + BlockUnit.UNIT_SIZE - BOUND_WIDTH_OF_WALL, y + BlockUnit.UNIT_SIZE - BOUND_WIDTH_OF_WALL);
            canvas.drawRoundRect(rel, 8, 8, paintBlock);
        }
        // 隨機產生方塊
        len = allBlockUnits.size();
        // 繪製方塊
        // Toast.makeText(context, "" + len, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < len; i++) {
            int x = allBlockUnits.get(i).x;
            int y = allBlockUnits.get(i).y;
            paintBlock.setColor(color[allBlockUnits.get(i).color]);
            rel = new RectF(x + BOUND_WIDTH_OF_WALL, y + BOUND_WIDTH_OF_WALL,
                    x + BlockUnit.UNIT_SIZE - BOUND_WIDTH_OF_WALL, y + BlockUnit.UNIT_SIZE - BOUND_WIDTH_OF_WALL);
            canvas.drawRoundRect(rel, 8, 8, paintBlock);
        }
    }

    // 開始遊戲
    public void startGame() {
        gameStatus = true;
        runningStatus = true;
        if (mainThread == null || !mainThread.isAlive()) {
            getNewBlock();
            mainThread = new Thread(new MainThread());
            mainThread.start();
        }

    }

    // 暫停遊戲
    public void pauseGame() {
        runningStatus = false;
    }

    // 繼續遊戲
    public void continueGame() {
        runningStatus = true;
    }

    // 停止遊戲
    public void stopGame() {
        // 然後把執行緒釋放掉
        runningStatus = false;
        gameStatus = false;
        mainThread.interrupt();
        blockUnits.clear();
        allBlockUnits.clear();
        score = 0;
        invalidate();
    }

    // 往左移動
    public void toLeft() {
        if (BlockUnit.toLeft(blockUnits, max_x, allBlockUnits)) {
            xx = xx - BlockUnit.UNIT_SIZE;
        }
        invalidate();
    }

    // 往右移動
    public void toRight() {
        if (BlockUnit.toRight(blockUnits, max_x, allBlockUnits)) {
            xx = xx + BlockUnit.UNIT_SIZE;
        }
        invalidate();
    }

    // 順時針旋轉
    public void route() {
        // 如果方塊是正方形，有轉跟沒轉一樣，就不轉了
        if (blockType == 3) {
            return;
        }
        if (routeBlockUnitBufs.size() != blockUnits.size()) {
            routeBlockUnitBufs = tetrisBlock.getUnits(xx, yy);
        }
        for (int i = 0; i < blockUnits.size(); i++) {
            routeBlockUnitBufs.get(i).x = blockUnits.get(i).x;
            routeBlockUnitBufs.get(i).y = blockUnits.get(i).y;
        }
        for (BlockUnit blockUnit : routeBlockUnitBufs) {
            int tx = blockUnit.x;
            int ty = blockUnit.y;
            blockUnit.x = -(ty - yy) + xx;
            blockUnit.y = tx - xx + yy;
        }
        routeTran(routeBlockUnitBufs);
        if (!BlockUnit.canRoute(routeBlockUnitBufs, allBlockUnits)) {
            // Toast.makeText(father, "不可以旋轉", Toast.LENGTH_SHORT).show();
            return;
        }
        for (BlockUnit blockUnit : blockUnits) {
            int tx = blockUnit.x;
            int ty = blockUnit.y;
            blockUnit.x = -(ty - yy) + xx;
            blockUnit.y = tx - xx + yy;
        }
        routeTran(blockUnits);
        invalidate();
    }

    // 方塊旋轉之後可能會超出邊界，透過遞迴判断是否有超出邊界的部分
    // 如果有，就左右移動，把它挪到邊界裡面
    public void routeTran(List<BlockUnit> blockUnitsBuf) {
        boolean needLeftTran = false;
        boolean needRightTran = false;
        for (BlockUnit u : blockUnitsBuf) {
            if (u.x < beginPoint) {
                needLeftTran = true;
            }
            if (u.x > max_x - BlockUnit.UNIT_SIZE) {
                needRightTran = true;
            }
        }
        if (needLeftTran || needRightTran) {
            for (BlockUnit u : blockUnitsBuf) {
                if (needLeftTran) {
                    u.x = u.x + BlockUnit.UNIT_SIZE;
                } else if (needRightTran) {
                    u.x = u.x - BlockUnit.UNIT_SIZE;
                }
            }
            routeTran(blockUnitsBuf);
        } else {
            return;
        }
    }

    // 取得新方塊
    private void getNewBlock() {
        // 新的方塊座標，在 x 軸正中間，y 軸起始位置
        this.xx = beginPoint + (num_x / 2) * BlockUnit.UNIT_SIZE;
        this.yy = beginPoint;
        if (blockUnitBufs.size() == 0) {
            // 遊戲第一次開始，先初始化一個方塊
            blockUnitBufs = tetrisBlock.getUnits(xx, yy);
        }
        blockUnits = blockUnitBufs;
        blockType = tetrisBlock.blockType;
        blockUnitBufs = tetrisBlock.getUnits(xx, yy);
        // 顯示下一個要出現的方塊
        if (father != null) {
            father.setNextBlockView(blockUnitBufs, (num_x / 2) * BlockUnit.UNIT_SIZE);
        }
    }

    // 遊戲主執行緒
    private class MainThread implements Runnable {
        @Override
        public void run() {
            while (gameStatus) {
                while (runningStatus) {
                    if (BlockUnit.canMoveToDown(blockUnits, max_y, allBlockUnits)) {
                        // 判斷是不是可以降落
                        BlockUnit.toDown(blockUnits, max_y, allBlockUnits);
                        yy = yy + BlockUnit.UNIT_SIZE;
                    } else {
                         // 不可以降落(到底 or GameOver)，把当目前的方塊加到 allBlockUnits 中
                         // 判斷是不是有可以消除的方塊，然後在產生一個新的方塊
                        for (BlockUnit blockUnit : blockUnits) {
                            blockUnit.y = blockUnit.y + BlockUnit.UNIT_SIZE;
                            allBlockUnits.add(blockUnit);
                        }
                        for (BlockUnit u : blockUnits) {
                            // 更新每行網格中單元方塊的個數
                            int index = (int) ((u.y - beginPoint) / 50); // 计算所在行数
                            map[index]++;
                        }
                        // 每行最大個數
                        int end = (int) ((max_y - 50 - beginPoint) / BlockUnit.UNIT_SIZE);
                        int full = (int) ((max_x - 50 - beginPoint) / BlockUnit.UNIT_SIZE) + 1;
                        try {
                            Thread.sleep(GameConfig.SPEED);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i <= end; i++) {
                            // 消除需要消除的方塊（某一行中被塞滿了方塊，没有空白）
                            // 順序：先消除某一行，再移動這一行上面的方塊
                            if (map[i] >= full) {
                                BlockUnit.remove(allBlockUnits, i);
                                score += 100;
                                map[i] = 0;
                                for (int j = i; j > 0; j--)
                                    map[j] = map[j - 1];
                                map[0] = 0;
                                for (BlockUnit blockUnit : allBlockUnits) {
                                    if (blockUnit.y < (i * BlockUnit.UNIT_SIZE + beginPoint)) {
                                        blockUnit.y = blockUnit.y + BlockUnit.UNIT_SIZE;
                                    }
                                }
                            }
                        }
                        father.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 刷新分數
                                father.score.setText("" + score);
                                invalidate();
                            }
                        });
                        try {
                            Thread.sleep(GameConfig.SPEED * 3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        father.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getNewBlock();
                                score += 10;
                                father.score.setText("" + score);
                            }
                        });
                    }
                    father.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            invalidate();
                        }
                    });
                    try {
                        Thread.sleep(GameConfig.SPEED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}