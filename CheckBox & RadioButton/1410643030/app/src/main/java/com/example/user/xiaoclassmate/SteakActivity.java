package com.example.user.xiaoclassmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class SteakActivity extends AppCompatActivity {

    private Button steakRetunPage, steakCalculator;
    private CheckBox steakFL, steakSL;
    private EditText steakFLNumber, steakSLNumber;
    private TextView price, steakFLview, steakSLview;
    private int total_price;
    private RadioButton three_FL, five_FL, seven_FL, full_FL, three_SL, five_SL, seven_SL, full_SL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steak);

        steakRetunPage = (Button)findViewById(R.id.steakReturnMainButton);
        steakRetunPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SteakActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        steakFL = (CheckBox)findViewById(R.id.steakFLCheckBox);
        steakSL = (CheckBox)findViewById(R.id.steakSLCheckBox);
        steakFLNumber = (EditText)findViewById((R.id.steakFLnumber));
        steakSLNumber = (EditText)findViewById((R.id.steakSLnumber));
        steakCalculator = (Button)findViewById((R.id.steakCalculator));
        three_FL = (RadioButton)findViewById(R.id.three_FL);
        five_FL = (RadioButton)findViewById(R.id.five_FL);
        seven_FL = (RadioButton)findViewById(R.id.seven_FL);
        full_FL = (RadioButton)findViewById(R.id.full_FL);
        three_SL = (RadioButton)findViewById(R.id.three_SL);
        five_SL = (RadioButton)findViewById(R.id.five_SL);
        seven_SL = (RadioButton)findViewById(R.id.seven_SL);
        full_SL = (RadioButton)findViewById(R.id.full_SL);
        price = (TextView)findViewById(R.id.steakResult);
        steakFLview = (TextView)findViewById(R.id.steakFLView);
        steakSLview = (TextView)findViewById(R.id.steakSLView);

        steakCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(steakFL.isChecked()){
                    total_price += 300*(Integer.valueOf(steakFLNumber.getText().toString()));
                    if(three_FL.isChecked()){
                        steakFLview.setText("你選擇了三分熟 共 " + steakFLNumber.getText().toString() + " 份");
                    }
                    if(five_FL.isChecked()){
                        steakFLview.setText("你選擇了五分熟 共 " + steakFLNumber.getText().toString() + " 份");
                    }
                    if(seven_FL.isChecked()){
                        steakFLview.setText("你選擇了七分熟 共 " + steakFLNumber.getText().toString() + " 份");
                    }
                    if(full_FL.isChecked()){
                        steakFLview.setText("你選擇了全熟 共 " + steakFLNumber.getText().toString() + " 份");
                    }
                }
                if(steakSL.isChecked()){
                    total_price += 250*(Integer.valueOf(steakSLNumber.getText().toString()));
                    if(three_SL.isChecked()){
                        steakSLview.setText("你選擇了三分熟 共 " + steakSLNumber.getText().toString() + " 份");
                    }
                    if(five_SL.isChecked()){
                        steakSLview.setText("你選擇了五分熟 共 " + steakSLNumber.getText().toString() + " 份");
                    }
                    if(seven_SL.isChecked()){
                        steakSLview.setText("你選擇了七分熟 共 " + steakSLNumber.getText().toString() + " 份");
                    }
                    if(full_SL.isChecked()){
                        steakSLview.setText("你選擇了全熟 共 " + steakSLNumber.getText().toString() + " 份");
                    }
                }
                if (total_price != 0){
                    price.setText("總計 " + String.valueOf(total_price));
                    total_price = 0;
                }
            }
        });
    }
}
