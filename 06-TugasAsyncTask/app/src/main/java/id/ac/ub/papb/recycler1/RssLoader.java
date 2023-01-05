package id.ac.ub.papb.recycler1;

import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class RssLoader extends AsyncTask<String, String, List<String>> {
    private final static String SOURCE = "https://medium.com/feed/tag/programming";

    private RecyclerView recyclerView;
    private ArrayList<String> listItems;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;

    public RssLoader() {
        listItems = new ArrayList<>();
    }

    public void setRecyclerView(RecyclerView rv) {
        this.recyclerView = rv;
    }

    public void setAdapter(MainAdapter adapter) {
        this.adapter = adapter;
    }

    public void setLayoutManager(LinearLayoutManager lm) {
        this.layoutManager = lm;
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        RssParser parser = new RssParser();
        try {
            String xml = parser.loadRssFromUrl(SOURCE);
            List<String> listItem = parser.parseRssFromUrl(xml);
            return listItem;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> s) {
        super.onPostExecute(s);
        listItems = (ArrayList<String>) s;
        adapter.setData(listItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}

