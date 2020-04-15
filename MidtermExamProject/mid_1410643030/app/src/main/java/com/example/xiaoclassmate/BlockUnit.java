package com.example.xiaoclassmate;

import java.util.List;

// BlockUnit.java 方塊的降落，左右移動，判斷是否可以旋轉。

public class BlockUnit {
    public static final int UNIT_SIZE = 50;
    public static final int BEGIN = 10;
    public int color;
    // 單元方塊的座標
    public int x, y;

    public BlockUnit() {
    }

    public BlockUnit(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // 判斷方塊是否可以移動：是否在邊緣、是否會跟其他方塊重疊
    // blockUnits = 目前正在降落的方塊
    // max_x = 遊戲主介面 x 軸的最大值
    // allBlockUnits = 所有單元方塊
    // return = 可以移動(True)、不能移動(False)

    // 判斷方塊是否可以向左移動
    public static boolean canMoveToLeft(List<BlockUnit> blockUnits, int max_x, List<BlockUnit> allBlockUnits) {
        for (BlockUnit blockUnit : blockUnits) {
            int x = blockUnit.x;
            if (x - UNIT_SIZE < BEGIN) {
                return false;
            }
            int y = blockUnit.y;
            if (isSameUnit(x - UNIT_SIZE, y, allBlockUnits)) {
                return false;
            }
        }
        return true;
    }

    // 判斷方塊是否可以向右移動
    public static boolean canMoveToRight(List<BlockUnit> blockUnits, int max_x, List<BlockUnit> allBlockUnits) {
        for (BlockUnit blockUnit : blockUnits) {
            int x = blockUnit.x;
            if (x + UNIT_SIZE > max_x - UNIT_SIZE) {
                return false;
            }
            int y = blockUnit.y;
            if (isSameUnit(x + UNIT_SIZE, y, allBlockUnits)) {
                return false;
            }
        }
        return true;
    }

    // 判斷方塊是否可以向下移動
    public static boolean canMoveToDown(List<BlockUnit> blockUnits, int max_y, List<BlockUnit> allBlockUnits) {
        for (BlockUnit blockUnit : blockUnits) {
            int x = blockUnit.x;
            int y = blockUnit.y + UNIT_SIZE * 2;
            if (y > max_y - UNIT_SIZE) {
                return false;
            }
            if (isSameUnit(x, y, allBlockUnits)) {
                return false;
            }
        }
        return true;
    }

    // 判斷方塊是否可以旋轉
    public static boolean canRoute(List<BlockUnit> blockUnits, List<BlockUnit> allBlockUnits){
        for (BlockUnit blockUnit: blockUnits) {
            if(isSameUnit(blockUnit.x, blockUnit.y, allBlockUnits)){
                return false;
            }
        }
        return true;
    }

    // return = 移動成功(True)、移動失敗(Fasle)
    // 目前方塊往左移動一格
    public static boolean toLeft(List<BlockUnit> blockUnits, int max_x, List<BlockUnit> allBlockUnits) {
        if (canMoveToLeft(blockUnits, max_x, allBlockUnits)) {
            for (BlockUnit blockUnit : blockUnits) {
                blockUnit.x = blockUnit.x - UNIT_SIZE;
            }
            return true;
        }
        return false;
    }

    // 目前方塊往右移動一格
    public static boolean toRight(List<BlockUnit> blockUnits, int max_x, List<BlockUnit> allBlockUnits) {
        if (canMoveToRight(blockUnits, max_x, allBlockUnits)) {
            for (BlockUnit blockUnit : blockUnits) {
                blockUnit.x = blockUnit.x + UNIT_SIZE;
            }
            return true;
        }
        return false;
    }

    // 目前方塊往下移動一格
    public static void toDown(List<BlockUnit> blockUnits, int max_Y, List<BlockUnit> allBlockUnits) {
        for (BlockUnit blockUnit : blockUnits) {
            blockUnit.y = blockUnit.y + BlockUnit.UNIT_SIZE;
        }
    }

    // 判斷單元方塊是否和所有方塊疊合
    public static boolean isSameUnit(int x, int y, List<BlockUnit> allBlockUnits) {
        for (BlockUnit blockUnit : allBlockUnits) {
            if (Math.abs(x - blockUnit.x) < UNIT_SIZE && Math.abs(y - blockUnit.y) < UNIT_SIZE) {
                return true;
            }
        }
        return false;
    }

    // 刪除第 J 行上面的單元方塊
    public static void remove(List<BlockUnit> allBlockUnits, int j) {
        for (int i = allBlockUnits.size() - 1; i >= 0; i--) {
            // 逆向找，根據 Y 座標計算單元所在行，如果是 J 行則從 units 中刪除
            if ((int) ((allBlockUnits.get(i).y - BEGIN) / 50) == j)
                allBlockUnits.remove(i);
        }
    }
}
