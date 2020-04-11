package com.example.user.xiaoclassmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class PizzaActivity extends AppCompatActivity {

    private Button pizzaRetunPage, pizzaCalculator;
    private CheckBox Piz1, Piz2, Piz3, Piz4, Piz5, Piz6, Piz7, Piz8;
    private EditText PizNumber1, PizNumber2, PizNumber3, PizNumber4, PizNumber5, PizNumber6, PizNumber7, PizNumber8;
    private TextView price;
    private int total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        pizzaRetunPage = (Button)findViewById(R.id.pizzaReturnMainButton);
        pizzaRetunPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PizzaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Piz1 = (CheckBox)findViewById(R.id.checkBox1);
        Piz2 = (CheckBox)findViewById(R.id.checkBox2);
        Piz3 = (CheckBox)findViewById(R.id.checkBox3);
        Piz4 = (CheckBox)findViewById(R.id.checkBox4);
        Piz5 = (CheckBox)findViewById(R.id.checkBox5);
        Piz6 = (CheckBox)findViewById(R.id.checkBox6);
        Piz7 = (CheckBox)findViewById(R.id.checkBox7);
        Piz8 = (CheckBox)findViewById(R.id.checkBox8);
        PizNumber1 = (EditText)findViewById(R.id.editText1);
        PizNumber2 = (EditText)findViewById(R.id.editText2);
        PizNumber3 = (EditText)findViewById(R.id.editText3);
        PizNumber4 = (EditText)findViewById(R.id.editText4);
        PizNumber5 = (EditText)findViewById(R.id.editText5);
        PizNumber6 = (EditText)findViewById(R.id.editText6);
        PizNumber7 = (EditText)findViewById(R.id.editText7);
        PizNumber8 = (EditText)findViewById(R.id.editText8);
        pizzaCalculator = (Button)findViewById(R.id.pizzaCalculator);
        price = (TextView)findViewById(R.id.pizzaResult);

        pizzaCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Piz1.isChecked()){
                    total_price += 460*(Integer.valueOf(PizNumber1.getText().toString()));
                }
                if (Piz2.isChecked()){
                    total_price += 500*(Integer.valueOf(PizNumber2.getText().toString()));
                }
                if (Piz3.isChecked()){
                    total_price += 250*(Integer.valueOf(PizNumber3.getText().toString()));
                }
                if (Piz4.isChecked()){
                    total_price += 370*(Integer.valueOf(PizNumber4.getText().toString()));
                }
                if (Piz5.isChecked()){
                    total_price += 440*(Integer.valueOf(PizNumber5.getText().toString()));
                }
                if (Piz6.isChecked()){
                    total_price += 390*(Integer.valueOf(PizNumber6.getText().toString()));
                }
                if (Piz7.isChecked()){
                    total_price += 390*(Integer.valueOf(PizNumber7.getText().toString()));
                }
                if (Piz8.isChecked()){
                    total_price += 430*(Integer.valueOf(PizNumber8.getText().toString()));
                }
                if (total_price != 0){
                    price.setText(String.valueOf(total_price));
                    total_price = 0;
                }
            }
        });


    }
}
