package id.ac.ub.papb.recycler1;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RssParser {
    private static String TAG_ITEM = "item";
    private static String TAG_TITLE = "title";
    private static String TAG_CHANNEL = "channel";

    public String loadRssFromUrl(String url) throws IOException {
        Log.d("RSSPARSER", "Start rss parser");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String xml = response.body().string();
        Log.d("RSSPARSER", "xml " + xml);
        return xml;
    }

    public List<String> parseRssFromUrl(String xml) throws ParserConfigurationException, IOException, SAXException {
        List<String> list = new ArrayList<>();
        DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = builder.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
        Document doc = db.parse(is);
        NodeList nodeList = doc.getElementsByTagName(TAG_CHANNEL);
        Element e = (Element) nodeList.item(0);

        NodeList items = e.getElementsByTagName(TAG_ITEM);
        for (int i = 0; i < items.getLength(); i++) {
            Element e1 = (Element) items.item(i);
            NodeList n = e1.getElementsByTagName(TAG_TITLE);
            Node ne = n.item(0);
            Node child = ne.getFirstChild();
            String judul = child.getNodeValue();
            Log.d("RSSPARSER", judul);
            list.add(judul);
        }
        return list;
    }
}

