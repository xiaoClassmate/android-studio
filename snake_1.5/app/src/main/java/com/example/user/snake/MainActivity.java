package com.example.user.snake;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import android.widget.ImageView;
import android.widget.TextView;

class Drawer extends Handler {

    public float gameHeightPixel = 1500;
    public float gameWidthPixel = 1000;
    public int gameHeight = 45;
    public int gameWidth = 30;
    public float waitSpeed = 10;
    public float count = 0;



    public Controller controller;
    private float x1,y1;//按下时的坐标 大圆
    private float x2,y2;//移动后的坐标 小圆
    private float r1 = 160;
    private float r2 = 80;

    public SnackRule snackRule;
    LinearLayout layout;
    DrawView view ;
    int[][] map = new int[this.gameHeight][this.gameWidth];

    public Bitmap background_club;
    public Bitmap background_diamond;
    public Bitmap background_heart;
    public Bitmap background_peach;

    public Bitmap point_club;
    public Bitmap point_diamond;
    public Bitmap point_heart;
    public Bitmap point_peach;
    public Bitmap controller_img;

    public Drawer(LinearLayout layout, Context context) {
        super();
        this.initMap();
        this.layout = layout;
        this.view = new DrawView(context);
        this.snackRule = new SnackRule(this.map);
        this.controller = new Controller();
//        this.gameHeightPixel = gameHeightPixel;
//        this.gameWidthPixel = gameWidthPixel;

        background_club = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_club);
        background_diamond = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_diamond);
        background_heart = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_heart);
        background_peach = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_peach);

        point_club = BitmapFactory.decodeResource(context.getResources(), R.drawable.point_club);
        point_diamond = BitmapFactory.decodeResource(context.getResources(), R.drawable.point_diamond);
        point_heart = BitmapFactory.decodeResource(context.getResources(), R.drawable.point_heart);
        point_peach = BitmapFactory.decodeResource(context.getResources(), R.drawable.point_peach);

        controller_img = BitmapFactory.decodeResource(context.getResources(), R.drawable.controller_img);

        this.initCanvas();
    }
    private void initMap(){
        for(int i = 0; i < this.gameHeight; i++){
            for(int j = 0; j < this.gameWidth; j++){
                if(i == 0 || j == 0 || i == gameHeight-1 || j == gameWidth-1){
                    this.map[i][j] = -6;
                }
                else{
                    this.map[i][j] = 0;
                }
            }
        }
        this.map[15][15] = 2;
    }
    public void restart(){
        this.initMap();
        this.snackRule = new SnackRule(this.map);
        this.snackRule.isEnd=false;

    }
    public void set_hw(float h,float w){
        this.gameHeightPixel = h;
        this.gameWidthPixel = w;
    }
    @Override
    public void handleMessage(Message msg) {
        count++;
        if(count % 10 == 0){
            this.snackRule.nextIter();
        }

        this.refresh();
    }
    private void initCanvas(){
        view.setMinimumHeight((int)gameHeightPixel);
        view.setMinimumWidth((int)gameWidthPixel);
        //通知view組件重繪
        this.refresh();
    }
    void refresh(){
//        Log.d("refresh:", "refresh:");
        layout.removeView(view);
        view.invalidate();
        layout.addView(view);
    }
    public class DrawView extends View{

        public DrawView(Context context) {
            super(context);

            // TODO Auto-generated constructor stub

        }
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            switch(snackRule.direction){
                case "top":
                    drawImage(canvas, background_peach, 0, 0, (int)gameWidthPixel, (int)gameHeightPixel, 0, 0);
                    break;
                case "down":
                    drawImage(canvas, background_heart, 0, 0, (int)gameWidthPixel, (int)gameHeightPixel, 0, 0);
                    break;
                case "left":
                    drawImage(canvas, background_diamond, 0, 0, (int)gameWidthPixel, (int)gameHeightPixel, 0, 0);
                    break;
                case "right":
                    drawImage(canvas, background_club, 0, 0, (int)gameWidthPixel, (int)gameHeightPixel, 0, 0);
                    break;
            }

            for(int i = 0; i < gameHeight; i++){
                for(int j = 0; j < gameWidth; j++){
                    int top   = i     * (int)(gameHeightPixel/gameHeight);
                    int down  = (i+1) * (int)(gameHeightPixel/gameHeight);
                    int left  = j     * (int)(gameWidthPixel /gameWidth );
                    int right = (j+1) * (int)(gameWidthPixel /gameWidth );
                    int height = (int)(gameHeightPixel /gameHeight );
                    int width = (int)(gameWidthPixel /gameWidth );
                    switch(map[i][j]){
                        case -6:
                            this.drawRect(paint, canvas, top, down, left, right, "#000000");
                            break;
                        case -5:
                            this.drawRect(paint, canvas, top, down, left, right, "#0000ff");break;
                        case -4:
                            this.drawRect(paint, canvas, top, down, left, right, "#f0f0f0");break;
                        case -3:
                            this.drawRect(paint, canvas, top, down, left, right, "#0f0f0f");break;
                        case -2:
                            this.drawRect(paint, canvas, top, down, left, right, "#ff0f0f");break;
                        case -1:
                            this.drawRect(paint, canvas, top, down, left, right, "#fff0f0");break;
                        case 0:
                            break;
                        default:
                            this.drawRect(paint, canvas, top, down, left, right, "#ffffff");break;
                    }

                    top = snackRule.foodList[0] * height-height/2;
                    left = snackRule.foodList[1] * width-width/2;
                    drawImage(canvas, point_peach, left, top, width+width/2, height+height/2, 0, 0);

                    top = snackRule.foodList[2] * height-height/2;
                    left = snackRule.foodList[3] * width-width/2;
                    drawImage(canvas, point_heart, left, top, width+width/2, height+height/2, 0, 0);

                    top = snackRule.foodList[4] * height-height/2;
                    left = snackRule.foodList[5] * width-width/2;
                    drawImage(canvas, point_diamond, left, top, width+width/2, height+height/2, 0, 0);

                    top = snackRule.foodList[6] * height-height/2;
                    left = snackRule.foodList[7] * width-width/2;
                    drawImage(canvas, point_club, left, top, width+width/2, height+height/2, 0, 0);
                }
            }
//            Log.d("value:","gg"+controller.controller_down);
            if (controller.controller_down){
                my.re.left = x1 - r1;
                my.re.top = y1 - r1;
                my.re.right = x1 + r1;
                my.re.bottom = y1 + r1;
                canvas.drawBitmap(controller_img, null, my.re, paint); //画大圆
                Log.d("value:","show_the_controller"+my.re);
//                Log.d("onTouch", "onTouch "+String.valueOf(tempArray[i]));
                my.re.left = x2 - r2;
                my.re.top = y2 - r2;
                my.re.right = x2 + r2;
                my.re.bottom = y2 + r2;
                canvas.drawBitmap(controller_img, null, my.re, paint); //画小圆
            }
        }
//        public class DrawControllerView extends View{
//            public DrawControllerView(Context context) {
//                super(context);
//                // TODO Auto-generated constructor stub
//            }
//            protected void onDraw(Canvas canvas) {
//                super.onDraw(canvas);
//                Paint paint = new Paint();
//                if (controller.controller_down){
//                    my.re.left = x1 - r1;
//                    my.re.top = y1 - r1;
//                    my.re.right = x1 + r1;
//                    my.re.bottom = y1 + r1;
//                    canvas.drawBitmap(controller_img, null, my.re, paint); //画大圆
//                    Log.d("value:","gg"+my.re);
////                Log.d("onTouch", "onTouch "+String.valueOf(tempArray[i]));
//                    my.re.left = x2 - r2;
//                    my.re.top = y2 - r2;
//                    my.re.right = x2 + r2;
//                    my.re.bottom = y2 + r2;
//                    canvas.drawBitmap(controller_img, null, my.re, paint); //画小圆
//                }
//            }
//        }
        private void drawRect(Paint paint, Canvas canvas, int top, int down,int left,int right, String color){
            // 正方形繪畫
            paint.setColor(Color.parseColor(color));
//            canvas.drawText("正方形：",450,20, paint);
            paint.setStyle(Paint.Style.FILL);//設置填滿
            canvas.drawRect(left, top, right, down, paint);// 正方形
            //            // (520, 0) 左上頂點座標
            // (540, 20) 右下頂點座標

        }
        public void drawImage(Canvas canvas, Bitmap blt, int x, int y,
                                     int w, int h, int bx, int by) {
            Rect src = new Rect();// 图片 >>原矩形
            Rect dst = new Rect();// 屏幕 >>目标矩形

            src.left = bx;
            src.top = by;
            src.right = bx + w;
            src.bottom = by + h;

            dst.left = x;
            dst.top = y;
            dst.right = x + w;
            dst.bottom = y + h;
            // 画出指定的位图，位图将自动--》缩放/自动转换，以填补目标矩形
            // 这个方法的意思就像 将一个位图按照需求重画一遍，画后的位图就是我们需要的了
            canvas.drawBitmap(blt, null, dst, null);
            src = null;
            dst = null;
        }

    }
    class Controller{
        boolean controller_down = false;
        public void down(float x,float y){
            this.controller_down = true;
            x1 = x;
            y1 = y;
        }
        public void move(float x,float y){
            x2 = x;
            y2 = y;
        }
        public void up(){
            this.controller_down = false;
        }
    }
    static class my{
        public static RectF re=new RectF();
    }
    class SnackRule{
        int[][] map;
        int[] headCenter = {15,15};
        boolean isEnd = false;
        String direction = "top";
        String nextDirection = "top";
        int snackLength = 3;
        int score = 0;
        public int[] foodList = {0,0, 0,0, 0,0, 0,0};

        public SnackRule(int[][] map){
            this.map = map;
            this.newFood(0);
            this.newFood(2);
            this.newFood(4);
            this.newFood(6);
        }

        public void setDirection(String direction){
            if(!this.isEnd) {
                if (direction == "top" && this.direction != "down") {
                    this.nextDirection = "top";
                }
                if (direction == "down" && this.direction != "top") {
                    this.nextDirection = "down";
                }
                if (direction == "right" && this.direction != "left") {
                    this.nextDirection = "right";
                }
                if (direction == "left" && this.direction != "right") {
                    this.nextDirection = "left";
                }
            }

        }
        private boolean isFood(int []position){
//            Log.d("isFood: ", "isFood: ");
            for(int i = 0; i < 8; i+=2){
                if(position[0] == this.foodList[i] && position[1] == this.foodList[i+1]){
                    return true;
                }
            }
            return false;
        }
        private boolean isBody(int []position){
//            Log.d("isFood: ", "isFood: ");
            if(map[position[0]][position[1]] > 0){
                return true;
            }
            return false;
        }
        private boolean isWall(int []position){
//            Log.d("isWall: ", "isWall: ");
            if(position[0] > gameHeight-1 || position[1] > gameWidth-1){
                return true;
            }
            else if(position[0] < 0 || position[1] < 0){
                return true;
            }
            else if(map[position[0]][position[1]] < -1 && map[position[0]][position[1]] >= -6){
                return true;
            }
            return false;
        }
        private void refreshSnackBody(){
            for(int i = 0; i < gameHeight; i++){
                for(int j = 0; j < gameWidth; j++){
                    if(this.map[i][j] > 0){
                        this.map[i][j] += 1;
                    }
                    if(this.map[i][j] > snackLength){
                        this.map[i][j] = 0;
                    }

                }
            }
        }
        private void getFood(int []position){
            this.snackLength+=1;
            this.score+=10;
            for(int i = 0; i < 8; i+=2){
                if(position[0] == this.foodList[i] && position[1] == this.foodList[i+1]){
                    this.newFood(i);
                    break;
                }
            }
            Log.d("ERROR: ", "can't not find food in get food func");
        }
        private boolean isGetFood(int []position){
            if(position[0] == this.foodList[0] && position[1] == this.foodList[1] && this.nextDirection.equals("top")){
                return true;
            }
            if(position[0] == this.foodList[2] && position[1] == this.foodList[3] && this.nextDirection.equals("down")){
                return true;
            }
            if(position[0] == this.foodList[4] && position[1] == this.foodList[5] && this.nextDirection.equals("left")){
                return true;
            }
            if(position[0] == this.foodList[6] && position[1] == this.foodList[7] && this.nextDirection.equals("right")){
                return true;
            }
            return false;
        }
        private void newFood(int Offset){
            while(true){
                int [] tempPoint = {(int)(Math.random()* gameHeight), (int)(Math.random()* gameWidth)};
                if(!this.isWall(tempPoint) && !this.isBody(tempPoint) && !this.isFood(tempPoint)){
                    if(tempPoint[0] >= gameHeight-2 || tempPoint[1] >= gameWidth-2){
                        continue;
                    }
                    else if(tempPoint[0] <= 1 || tempPoint[1] <= 1){
                        continue;
                    }
                    this.foodList[Offset]   = tempPoint[0];
                    this.foodList[Offset+1] = tempPoint[1];
                    break;
                }
            }
        }
        private void gameOver(){
            this.isEnd = true;
            return;
        }
        private int[] nextPoint(){
            int []nextPoint = {headCenter[0], headCenter[1]};
            if(this.nextDirection.equals("top")){
                nextPoint[0] -= 1;
            }
            if(this.nextDirection.equals("down")){
                nextPoint[0] += 1;
            }
            if(this.nextDirection.equals("left")){
                nextPoint[1] -= 1;
            }
            if(this.nextDirection.equals("right")){
                nextPoint[1] += 1;
            }
            this.direction = this.nextDirection;
            return nextPoint;
        }
        public void nextIter(){
            if(this.isEnd){
                return;
            }
            int[] newPoint = nextPoint();
            if(this.isWall(newPoint) || this.isBody(newPoint)){
                this.gameOver();
                return;
            }
            if(this.isGetFood(newPoint)){
                this.getFood(newPoint);
            }
            this.map[newPoint[0]][newPoint[1]] = 1;
            this.refreshSnackBody();
            this.headCenter[0] = newPoint[0];
            this.headCenter[1] = newPoint[1];
        }
    }
}

public class MainActivity extends AppCompatActivity {

    Drawer handler;

    private Button buttonLeft;
    private Button buttonRight;
    private Button buttonTop;
    private Button buttonDown;
    private Button buttonRestart;
    private LinearLayout mainView;
    private TextView textView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        handler.gameHeightPixel = dis.heightPixels;
//        handler.gameHeight = dis.widthPixels;

        mainView = (LinearLayout) findViewById(R.id.root);
        buttonRestart = findViewById(R.id.restart);
        textView = (TextView) findViewById(R.id.textView);
        buttonRestart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                handler.restart();
            }
        });
        mainView.setOnTouchListener(new View.OnTouchListener() {

            float tempX;
            float tempY;
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        float newX = event.getX();
                        float newY = event.getY();
                        handler.controller.move(newX,newY);

                        float horizontalSpeed = newX - tempX;
                        float verticalSpeed = newY - tempY;
                        int [] tempArray = new int [4];
                        if(horizontalSpeed > 0){
                            tempArray[0] = (int)horizontalSpeed;
                            tempArray[1] = 0;
                        }
                        else{
                            tempArray[0] = 0;
                            tempArray[1] = (int)-horizontalSpeed;
                        }
                        if(verticalSpeed > 0){
                            tempArray[2] = (int)verticalSpeed;
                            tempArray[3] = 0;
                        }
                        else{
                            tempArray[2] = 0;
                            tempArray[3] = (int)-verticalSpeed;
                        }

                        int max = 0, maxI = 0;
                        for(int i = 0; i < 4; i++){
//                            Log.d("onTouch", "onTouch "+String.valueOf(tempArray[i]));
                            if(tempArray[i] > max){
                                max = tempArray[i];
                                maxI = i;
                            }
                        }
                        Log.d("onTouch", "onTouchMove "+String.valueOf(maxI));
                        if(maxI == 0)  handler.snackRule.setDirection("right");
                        if(maxI == 1)  handler.snackRule.setDirection("left");
                        if(maxI == 2)  handler.snackRule.setDirection("down");
                        if(maxI == 3)  handler.snackRule.setDirection("top");

//                        Log.d("onTouch", "onTouchDOWN X: "+String.valueOf(newX)+"Y:"+String.valueOf(newY));
                        break;
                    case MotionEvent.ACTION_DOWN:
                        Log.d("onTouch", "onTouchDOWN ");
                        tempX = event.getX();
                        tempY = event.getY();
                        handler.controller.up();
                        handler.controller.down(tempX,tempY);
                        handler.controller.controller_down=true;
                        textView.setText("point:"+handler.snackRule.score);
                        Log.d("onTouch", "onTouchDOWN ::::"+handler.controller.controller_down);
                    case MotionEvent.ACTION_UP:
                        Log.d("onTouch", "onTouchUP ");
//                        handler.controller.up();
//                        tempX = -1;
//                        tempY = -1;
                }
                return true;
            }
        });

        handler = new Drawer(mainView, this);
        DisplayMetrics dis = getResources().getDisplayMetrics();
        handler.set_hw(dis.heightPixels,dis.widthPixels);
        MyThread thread = new MyThread();
        thread.start();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            while(true){
                handler.sendEmptyMessage(0);
                try {
                    Thread.sleep((int)handler.waitSpeed);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
