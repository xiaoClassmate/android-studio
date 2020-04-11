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

public class PagebActiviy extends AppCompatActivity {

    private Button pretPageBtn;
    private EditText degreeC;
    private EditText degreeF;
    private Button calculatorC;
    private Button calculatorF;
//    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageb);

        pretPageBtn = findViewById(R.id.preButton);
        degreeC = findViewById(R.id.editText5);
        degreeF = findViewById(R.id.editText4);
//        result = findViewById(R.id.textView13);
        calculatorC = findViewById(R.id.buttonToC);
        calculatorF = findViewById(R.id.buttonToF);

        pretPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PagebActiviy.this,MainActivity.class);
                startActivity(intent);
            }
        });

        calculatorC.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                float fdegreeF = Float.parseFloat(degreeF.getText().toString());
                float FtoC = fdegreeF * 9 / 5 + 32;
                degreeC.setText(Float.toString(FtoC));
            }
        });

        calculatorF.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                float fdegreeC = Float.parseFloat(degreeC.getText().toString());
                float CtoF = (fdegreeC - 32)*5/9;
                degreeF.setText(Float.toString(CtoF));
            }
        });

    }
}
