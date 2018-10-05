package onestep.id.sinergiin.Pengrajin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.PengrajinDetailPembelianAdapter;
import onestep.id.sinergiin.Model.mPengrajinDetailPembelian;
import onestep.id.sinergiin.R;

public class pengrajinDetailPembelian extends AppCompatActivity {
    ListView listView;
    List<mPengrajinDetailPembelian>list = new ArrayList<>();
    PengrajinDetailPembelianAdapter adapter;

    TextView tv_notrans,tv_pembeli,tv_jumlah ;

    String id_detil_trans, nama_pembeli,total_trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengrajin_detail_pembelian);

        tv_notrans = findViewById(R.id.dp_nomorTransaksi);
        id_detil_trans = getIntent().getStringExtra("id_detil_transaksi");
        tv_notrans.setText(id_detil_trans);

        tv_pembeli = findViewById(R.id.dp_pemesan);
        nama_pembeli = getIntent().getStringExtra("nama_pembeli");
        tv_pembeli.setText(nama_pembeli);


        tv_jumlah = findViewById(R.id.tv_total_trans);
        total_trans = getIntent().getStringExtra("total_trans");
        tv_jumlah.setText(total_trans);

        listView = (ListView) findViewById(R.id.lv_pengrajinDetail);
        list.add(new mPengrajinDetailPembelian("1","https://my-test-11.slatic.net/p/sworld-dollhouse-miniature-1-12-wooden-rocking-chair-model-brown-5152-8494237-1-.jpg","Kursi Goyang","4","100.000"));
        list.add(new mPengrajinDetailPembelian("2","https://ae01.alicdn.com/kf/HTB1XeBvX43IL1JjSZPfq6ArUVXay/Bali.jpg_640x640.jpg","Tas Rotan","4","50.000"));
        adapter = new PengrajinDetailPembelianAdapter(pengrajinDetailPembelian.this,list);
        listView.setAdapter(adapter);
    }
}
