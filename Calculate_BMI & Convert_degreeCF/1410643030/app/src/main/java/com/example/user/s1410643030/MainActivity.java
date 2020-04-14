package com.example.user.s1410643030;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private EditText height;
    private EditText wieight;
    private TextView result;
    private Button calculator;
    private Button nextPageBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height = findViewById(R.id.editText1);
        wieight = findViewById(R.id.editText2);
        result = findViewById(R.id.textView4);
        calculator = findViewById(R.id.button4);
        nextPageBtn = findViewById(R.id.buttonNext);

        calculator.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                float fh = Float.parseFloat(height.getText().toString());
                float fw = Float.parseFloat(wieight.getText().toString());

                if (fh > 100 && fh < 200) {
                    fh = fh / 100;
                    fh = fh * fh;
                } else {
                    result.setText("請輸入正確的身高(單位：公分)");
                }

                float fresult = (fw / fh);

                if (fresult < 18.5) {
                    result.setText("你的BMI值：" + Float.toString(fresult) + " 體重過輕");
                } else if (18.5 <= fresult && fresult < 24) {
                    result.setText("你的BMI值：" + Float.toString(fresult) + "\n正常範圍");
                } else if (24 <= fresult && fresult < 27) {
                    result.setText("你的BMI值：" + Float.toString(fresult) + "\n過重");
                } else if (27 <= fresult && fresult < 30) {
                    result.setText("你的BMI值：" + Float.toString(fresult) + "\n輕度肥胖");
                } else if (30 <= fresult && fresult < 35) {
                    result.setText("你的BMI值：" + Float.toString(fresult) + "\n中度肥胖");
                } else {
                    result.setText("你的BMI值：" + Float.toString(fresult) + "\n重度肥胖");
                }
            }
        });

        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,PagebActiviy.class);
                startActivity(intent);
            }
        });
    }
}
