package com.example.user.xiaoclassmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button steakPage;
    private Button pizzaPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        steakPage = (Button)findViewById(R.id.steakPageButton);
        steakPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SteakActivity.class);
                startActivity(intent);
            }
        });

        pizzaPage = (Button)findViewById(R.id.pizzaPageButton);
        pizzaPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PizzaActivity.class);
                startActivity(intent);
            }
        });

    }
}
