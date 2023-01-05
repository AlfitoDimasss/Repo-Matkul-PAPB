package com.example.tugas4papbb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button btnBack;
    TextView tvOps, tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnBack = findViewById(R.id.btnBack);
        tvOps = findViewById(R.id.tvOps);
        tvRes = findViewById(R.id.tvRes);
        btnBack.setOnClickListener(this);
        Intent intent = getIntent();
        String res = intent.getStringExtra("result");
        String ops = intent.getStringExtra("operasi");
        tvOps.setText(ops);
        tvRes.setText(tvRes.getText().toString() + res);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}