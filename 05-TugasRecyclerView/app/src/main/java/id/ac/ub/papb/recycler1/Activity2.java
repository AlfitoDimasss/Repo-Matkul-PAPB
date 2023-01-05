package id.ac.ub.papb.recycler1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    TextView tvNim2, tvNama2;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        tvNim2 = findViewById(R.id.tvNim2);
        tvNama2 = findViewById(R.id.tvNama2);
        btnBack = findViewById(R.id.btnBack);
        Intent intent = getIntent();
        String nama = intent.getStringExtra("Nama");
        String nim = intent.getStringExtra("Nim");
        tvNim2.setText(nim);
        tvNama2.setText(nama);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}