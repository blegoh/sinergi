package onestep.id.sinergiin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.PembeliCartAdapter;
import onestep.id.sinergiin.Model.mPembeliCart;

public class PembeliCart extends AppCompatActivity {
    private ListView listView;
    private PembeliCartAdapter adapter;
    private List<mPembeliCart> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli_cart);
        listView = (ListView) findViewById(R.id.lv_cart);
        list.add(new mPembeliCart("1", "Lampu bambu", "Eldi", "10", "Rp.10.000", ""));
        list.add(new mPembeliCart("2", "Kayu", "Sofyan", "10", "Rp.20.000", ""));
        adapter = new PembeliCartAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
