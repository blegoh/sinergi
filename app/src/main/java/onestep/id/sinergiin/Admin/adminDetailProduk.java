package onestep.id.sinergiin.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;

public class adminDetailProduk extends AppCompatActivity {
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private NetworkImageView gambar;

    private String idProduk, namaProduk, deskripsi, harga, foto_produk, stok, penjual, jumlah;
    private TextView txtHarga, txtJumlahProduk, txtDeskripsi, txtNamaProduk, txtPenjual;
    private EditText etJumlah;
    private Button btnCart, btnBeli;
    private String id_user,token;

    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_produk);

        NumberFormat formatter = new DecimalFormat("#0,000");

        txtHarga = (TextView) findViewById(R.id.tv_hargaProduk);
        txtJumlahProduk = (TextView) findViewById(R.id.tv_stok);
        txtDeskripsi = (TextView) findViewById(R.id.tv_deskripsi);
        txtNamaProduk = (TextView) findViewById(R.id.tv_produk);
        txtPenjual = (TextView) findViewById(R.id.tv_penjual);
        etJumlah = (EditText) findViewById(R.id.et_jumlah);
        btnCart = (Button) findViewById(R.id.btn_cart);
        btnBeli = findViewById(R.id.btn_beli);
        gambar = (NetworkImageView) findViewById(R.id.iv_produk);

        idProduk = getIntent().getStringExtra("id_produk");
        namaProduk = getIntent().getStringExtra("nama_produk");
        deskripsi = getIntent().getStringExtra("deskripsi");
        harga = getIntent().getStringExtra("harga");
        foto_produk = getIntent().getStringExtra("foto_produk");
        stok = getIntent().getStringExtra("stok");
        penjual = getIntent().getStringExtra("penjual");
        foto_produk = getIntent().getStringExtra("foto_produk");


        String price = formatter.format(Double.parseDouble(harga)).replace(",000","k");

        txtHarga.setText("Rp." + price);
        txtNamaProduk.setText(namaProduk);
        txtJumlahProduk.setText("Stok : " + stok);
        txtPenjual.setText("penjual : " + penjual);
        txtDeskripsi.setText(deskripsi);
        gambar.setImageUrl(foto_produk, imageLoader);


        tinyDB = new TinyDB(getApplicationContext());
      /*  listIdProduk = tinyDB.getListString("id_produk");
        listJumlahPembelian=tinyDB.getListString("jumlah");*/

        id_user = tinyDB.getString("id_user");
        token = tinyDB.getString("token");

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
