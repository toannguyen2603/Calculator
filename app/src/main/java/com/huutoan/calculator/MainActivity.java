package com.huutoan.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, btnMinus, btnFactorial, btnSin, btnXXX, btnXX, btnQuote, btnDivisor, btnMultiple, btnPlus, btnDot;
    Button btnAc, btnDelete, btnEqual;
    TextView  InputText, OutputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b0 =findViewById(R.id.btn0);
        b1 =findViewById(R.id.btn1);
        b2 =findViewById(R.id.btn2);
        b3 =findViewById(R.id.btn3);
        b4 =findViewById(R.id.btn4);
        b5 =findViewById(R.id.bnt5);
        b6 =findViewById(R.id.btn6);
        b7 =findViewById(R.id.btn7);
        b8 =findViewById(R.id.btn8);
        b9 =findViewById(R.id.btn9);
        btnMinus =findViewById(R.id.btnMinus);
        btnQuote =findViewById(R.id.btnQuote);
        btnMultiple =findViewById(R.id.btnMul);
        btnPlus =findViewById(R.id.btnPlus);
        btnDot =findViewById(R.id.btnDot);
        btnDivisor =findViewById(R.id.btnDiv);
        btnAc =findViewById(R.id.btnAc);
        btnDelete =findViewById(R.id.btnDelete);
        btnEqual =findViewById(R.id.btnEqual);
    }
}