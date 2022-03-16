package com.huutoan.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, btnMinus, btnFactorial, btnSin, btnPercent, btnQuote, btnDivisor, btnMultiple, btnPlus, btnDot;
    Button btnAc, btnDelete, btnEqual;
    TextView  InputText, OutputText;

    @SuppressLint("SetTextI18n")
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
        btnSin = findViewById(R.id.btnSin);
        btnFactorial = findViewById(R.id.btnFac);
        btnPercent = findViewById(R.id.btnPer);

//        Get ID text view output
        InputText = findViewById(R.id.InputText);
        OutputText = findViewById(R.id.OutputText);

//        Onclick listener
        b0.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "0"));

        b1.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "1"));

        b2.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "2"));

        b3.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "3"));

        b4.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "4"));

        b5.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "5"));

        b6.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "6"));

        b7.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "7"));

        b8.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "8"));

        b9.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "9"));

        btnDot.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "."));

        btnAc.setOnClickListener((View view) -> {
            InputText.setText("");
            OutputText.setText("0");
        });

        btnDelete.setOnClickListener((View view) -> {
            String value = InputText.getText().toString();
            if (value.length() != 0) {
                value = value.substring(0, value.length() - 1);
                InputText.setText(value);
            } else {
                InputText.setText(value);
            }

        });

        btnPlus.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "+"));

        btnMinus.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "-"));

        btnMultiple.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "×"));

        btnDivisor.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "÷"));

        btnQuote.setOnClickListener((View view) -> {
            int cursorPos = InputText.getSelectionStart();
            Log.i("Message", String.valueOf(cursorPos));
            int openQuo = 0;
            int closeQuo = 0;
            int textLen = InputText.getText().length();

            for(int i = 0; i < cursorPos; i++){
                if(InputText.getText().toString().charAt(i) == '(') {
                    openQuo += 1;
                } else if (InputText.getText().toString().charAt(i) == ')'){
                    closeQuo += 1;
                }
            }
            if(openQuo == closeQuo || InputText.getText().toString().charAt(textLen - 1) == '('){
                InputText.setText("(");
            }
            if(openQuo < closeQuo || '(' != InputText.getText().toString().charAt(textLen - 1)){
                InputText.setText(")");
            }
        });

        btnSin.setOnClickListener((View view) -> InputText.setText(InputText.getText() + "sin"));

        btnFactorial.setOnClickListener((View view) -> {
            int value = Integer.parseInt(InputText.getText().toString());
            int fact = factorial(value);
            OutputText.setText(String.valueOf(fact));
            InputText.setText(value+"!");

        });

        btnEqual.setOnClickListener((View view) -> {
            String val = InputText.getText().toString();
            String replace = val.replace('÷','/').replace('×','*');
            double result = eval(replace);
            OutputText.setText(String.valueOf(result));
            InputText.setText(val);
        });

    }

//    Create factorial function
    int factorial (int n) {
        return (n == 1 || n == 0) ? 1 : n*factorial(n - 1);
    }

//    eval function

    //eval function
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func) {
                        case "sqrt":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
