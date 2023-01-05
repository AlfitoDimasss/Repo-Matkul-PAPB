package com.example.tugas3papb_b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// NAMA: ALFITO DIMAS PRASETYO
// NIM: 205150401111057

public class MainActivity extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPlus, btnMinus, btnMul, btnDiv, btnRes, btnDel;
    EditText result;
    String strOps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnRes = findViewById(R.id.btnRes);
        btnDel = findViewById(R.id.btnDel);

        result = findViewById(R.id.result);

        btn0.setOnClickListener(view -> {
            result.setText(result.getText() + "0");
            result.setSelection(result.getText().length());
        });
        btn1.setOnClickListener(view -> {
            result.setText(result.getText() + "1");
            result.setSelection(result.getText().length());
        });
        btn2.setOnClickListener(view -> {
            result.setText(result.getText() + "2");
            result.setSelection(result.getText().length());
        });
        btn3.setOnClickListener(view -> {
            result.setText(result.getText() + "3");
            result.setSelection(result.getText().length());
        });
        btn4.setOnClickListener(view -> {
            result.setText(result.getText() + "4");
            result.setSelection(result.getText().length());
        });
        btn5.setOnClickListener(view -> {
            result.setText(result.getText() + "5");
            result.setSelection(result.getText().length());
        });
        btn6.setOnClickListener(view -> {
            result.setText(result.getText() + "6");
            result.setSelection(result.getText().length());
        });
        btn7.setOnClickListener(view -> {
            result.setText(result.getText() + "7");
            result.setSelection(result.getText().length());
        });
        btn8.setOnClickListener(view -> {
            result.setText(result.getText() + "8");
            result.setSelection(result.getText().length());
        });
        btn9.setOnClickListener(view -> {
            result.setText(result.getText() + "9");
            result.setSelection(result.getText().length());
        });

        btnPlus.setOnClickListener(view -> {
            result.setText(result.getText() + "+");
            result.setSelection(result.getText().length());
        });
        btnMinus.setOnClickListener(view -> {
            result.setText(result.getText() + "-");
            result.setSelection(result.getText().length());
        });
        btnMul.setOnClickListener(view -> {
            result.setText(result.getText() + "x");
            result.setSelection(result.getText().length());
        });
        btnDiv.setOnClickListener(view -> {
            result.setText(result.getText() + "/");
            result.setSelection(result.getText().length());
        });

        btnRes.setOnClickListener(view -> {
            strOps = result.getText().toString();
            strOps = strOps.replaceAll("x", "*");
            String res = String.valueOf((int)eval(strOps));
            result.setText(res);
            result.setSelection(result.getText().length());
        });

        btnDel.setOnClickListener(view -> {
            result.setText(null);
            result.setHint("0");
            result.setSelection(result.getText().length());
        });

    }

//    Fungsi untuk mengevaluasi String
//    Fungsi didapat dari: https://stackoverflow.com/questions/3422673/how-to-evaluate-a-math-expression-given-in-string-form
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

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

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
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}