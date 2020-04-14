package com.example.xiaoclassmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton counter, scroll, poker, bmi, temperature, pizza, steak, tetris, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = (ImageButton)findViewById(R.id.counter);
        scroll = (ImageButton)findViewById(R.id.scroll);
        poker = (ImageButton)findViewById(R.id.poker);
        bmi = (ImageButton)findViewById(R.id.bmi);
        temperature = (ImageButton)findViewById(R.id.temperature);
        pizza = (ImageButton)findViewById(R.id.pizza);
        steak = (ImageButton)findViewById(R.id.steak);
        tetris = (ImageButton)findViewById(R.id.tetris);
        settings = (ImageButton)findViewById(R.id.settings);

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

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
