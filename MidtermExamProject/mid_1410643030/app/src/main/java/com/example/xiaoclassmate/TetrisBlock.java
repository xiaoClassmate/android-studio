package com.example.xiaoclassmate;

import java.util.ArrayList;
import java.util.List;

// TetrisBlock.java 產生不同形狀的方塊

public class TetrisBlock {
    private static final int TYPE_SUM = 7;
    // 方塊種類、方塊方向
    public int blockType, blockDirection;
    // 方塊顏色
    private int color;
    // 方塊座標
    private int x, y;

    public TetrisBlock() {
    }

    public TetrisBlock(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public List<BlockUnit> getUnits(int x, int y) {
        this.x = x;
        this.y = y;
        return returnUnit();
    }

    // 隨機產生一種方塊
    public List<BlockUnit> returnUnit() {
        // 方塊組成部分
        List<BlockUnit> units = new ArrayList<BlockUnit>();
        // random 一個種類的方塊
        blockType = (int) (Math.random() * TYPE_SUM) + 1;
        // 默認初始方向
        blockDirection = 1;
        // random 其中一種顏色
        color = (int) (Math.random() * 4) + 1;
        units.clear();
        switch (blockType) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    units.add(new BlockUnit(x + (-2 + i) * BlockUnit.UNIT_SIZE, y, color));
                }
                break;
            case 2:
                units.add(new BlockUnit(x + (-1 + 1) * BlockUnit.UNIT_SIZE, y - BlockUnit.UNIT_SIZE, color));
                for (int i = 0; i < 3; i++) {
                    units.add(new BlockUnit(x + (-1 + i) * BlockUnit.UNIT_SIZE, y, color));
                }
                break;
            case 3:
                for (int i = 0; i < 2; i++) {
                    units.add(new BlockUnit(x + (i - 1) * BlockUnit.UNIT_SIZE, y - BlockUnit.UNIT_SIZE, color));
                    units.add(new BlockUnit(x + (i - 1) * BlockUnit.UNIT_SIZE, y, color));
                }
                break;
            case 4:
                units.add(new BlockUnit(x + (-1 + 0) * BlockUnit.UNIT_SIZE, y - BlockUnit.UNIT_SIZE, color));
                for (int i = 0; i < 3; i++) {
                    units.add(new BlockUnit(x + (-1 + i) * BlockUnit.UNIT_SIZE, y, color));
                }
                break;
            case 5:
                units.add(new BlockUnit(x + (-1 + 2) * BlockUnit.UNIT_SIZE, y - BlockUnit.UNIT_SIZE, color));
                for (int i = 0; i < 3; i++) {
                    units.add(new BlockUnit(x + (-1 + i) * BlockUnit.UNIT_SIZE, y, color));
                }
                break;
            case 6:
                for (int i = 0; i < 2; i++) {
                    units.add(new BlockUnit(x + (-1 + i) * BlockUnit.UNIT_SIZE, y - BlockUnit.UNIT_SIZE, color));
                    units.add(new BlockUnit(x + i * BlockUnit.UNIT_SIZE, y, color));
                }
                break;
            case 7:
                for (int i = 0; i < 2; i++) {
                    units.add(new BlockUnit(x + i * BlockUnit.UNIT_SIZE, y - BlockUnit.UNIT_SIZE, color));
                    units.add(new BlockUnit(x + (-1 + i) * BlockUnit.UNIT_SIZE, y, color));
                }
                break;
        }
        return units;
    }
}
