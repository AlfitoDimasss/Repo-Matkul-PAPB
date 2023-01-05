package id.ac.ub.papb.recycler1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv1;
    private MainAdapter adapter;
    private LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv1 = findViewById(R.id.rv1);
        adapter = new MainAdapter(this);
        lm = new LinearLayoutManager(this);
        RssLoader rss = new RssLoader();
        rss.setRecyclerView(rv1);
        rss.setAdapter(adapter);
        rss.setLayoutManager(lm);
        rss.execute();
    }
}