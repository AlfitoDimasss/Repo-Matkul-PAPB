package id.ac.ub.papb.recycler1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rv1;
    public static String TAG = "RV1";
    EditText et1, et2;
    Button bt1;
    ArrayList<Mahasiswa> data;
    MahasiswaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv1 = findViewById(R.id.rv1);
        et1 = findViewById(R.id.etNim);
        et2 = findViewById(R.id.etNim2);
        bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(this);

        data = getData();
        adapter = new MahasiswaAdapter(this, data);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));
    }

    public ArrayList getData() {
        ArrayList<Mahasiswa> data = new ArrayList<>();
        List<String> nim = Arrays.asList(getResources().getStringArray(R.array.nim));
        List<String> nama = Arrays.asList(getResources().getStringArray(R.array.nama));
        for (int i = 0; i < nim.size(); i++) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.nim = nim.get(i);
            mhs.nama = nama.get(i);
            Log.d(TAG,"getData "+mhs.nim);
            data.add(mhs);
        }
        return data;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == bt1.getId()) {
            add();
            adapter.notifyDataSetChanged();
            et1.setText(null);
            et2.setText(null);
        }
    }

    public void add() {
        Mahasiswa mhs = new Mahasiswa();
        mhs.nim = et1.getText().toString();
        mhs.nama = et2.getText().toString();
        data.add(mhs);
    }
}