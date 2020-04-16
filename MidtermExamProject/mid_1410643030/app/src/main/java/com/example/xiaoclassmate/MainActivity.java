package com.example.xiaoclassmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton counter, scroll, poker, bmi, temperature, pizza, steak, tetris;
    private TextView title;
    private Button switch_button;
    private int theme = R.style.AppTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判斷是否有主題儲存
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_main);
        switch_button = (Button) findViewById(R.id.theme);
        switch_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                theme = (theme == R.style.AppTheme) ? R.style.NightTheme : R.style.AppTheme;
                MainActivity.this.recreate();
            }
        });

        counter = (ImageButton) findViewById(R.id.counter);
        scroll = (ImageButton) findViewById(R.id.scroll);
        poker = (ImageButton) findViewById(R.id.poker);
        bmi = (ImageButton) findViewById(R.id.bmi);
        temperature = (ImageButton) findViewById(R.id.temperature);
        pizza = (ImageButton) findViewById(R.id.pizza);
        steak = (ImageButton) findViewById(R.id.steak);
        tetris = (ImageButton) findViewById(R.id.tetris);
        title = (TextView) findViewById(R.id.myself_text);

        title.setTextColor(Color.rgb(0, 0, 0));

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CounterActivity.class);
                startActivity(intent);
            }
        });

        scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ScrollActivity.class);
                startActivity(intent);
            }
        });

        poker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PokerActivity.class);
                startActivity(intent);
            }
        });

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, BmiActivity.class);
                startActivity(intent);
            }
        });

        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TemperatureActivity.class);
                startActivity(intent);
            }
        });

        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PizzaActivity.class);
                startActivity(intent);
            }
        });

        steak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ScrollActivity.class);
                startActivity(intent);
            }
        });

        tetris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TetrisActivity.class);
                startActivity(intent);
            }
        });

//        settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, SettingsActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }
}
