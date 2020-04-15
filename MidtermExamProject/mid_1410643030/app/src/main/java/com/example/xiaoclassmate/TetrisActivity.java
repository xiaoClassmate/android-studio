package com.example.xiaoclassmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

// TetrisActivity.java 主界面，包含遊戲界面、方塊介面和控制

public class TetrisActivity extends AppCompatActivity {

    private NextBlockView nextBlockView;
    private TetrisView tetrisView;
    private TextView gameStatusTip;
    private Button returnMainPage;
    public TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tetris);
        nextBlockView = (NextBlockView) findViewById(R.id.nextBlockView1);
        tetrisView = (TetrisView) findViewById(R.id.tetrisViewAW1);
        tetrisView.setFather(this);
        gameStatusTip = (TextView) findViewById(R.id.game_staus_tip);
        score = (TextView) findViewById(R.id.score);
        returnMainPage = (Button)findViewById(R.id.returnMainPage);

        returnMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TetrisActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // 下一個方塊介面
    public void setNextBlockView(List<BlockUnit> blockUnits, int div_x) {
        nextBlockView.setBlockUnits(blockUnits, div_x);
    }

    // 遊戲狀態
    // 開始
    public void startGame(View view) {
        tetrisView.startGame();
        gameStatusTip.setText("執行遊戲");
    }
    // 暫停
    public void pauseGame(View view) {
        tetrisView.pauseGame();
        gameStatusTip.setText("暫停遊戲");
    }
    // 繼續
    public void continueGame(View view) {
        tetrisView.continueGame();
        gameStatusTip.setText("繼續遊戲");
    }
    // 停止
    public void stopGame(View view) {
        tetrisView.stopGame();
        score.setText(""+0);
        gameStatusTip.setText("停止遊戲");
    }

    // 遊戲控制
    // 往左移動
    public void toLeft(View view) {
        tetrisView.toLeft();
    }
    // 往右移動
    public void toRight(View view) {
        tetrisView.toRight();
    }
    //方塊旋轉
    public void toRoute(View view) {
        tetrisView.route();
    }

}
