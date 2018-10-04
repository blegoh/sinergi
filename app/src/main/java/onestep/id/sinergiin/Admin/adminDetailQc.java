package onestep.id.sinergiin.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.BaseApi;
import onestep.id.sinergiin.Pembeli.pembeli;
import onestep.id.sinergiin.Pembeli.pembeliDetailCommerce;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;

public class adminDetailQc extends AppCompatActivity {
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private NetworkImageView gambar;
    Button btnTolak,btnRevisi,btnTerima;
    EditText et_catatan;
    private String idProduk, namaProduk, deskripsi, harga, foto_produk, stok, penjual, jumlah,catatan;
    private String id_user, token;
    private ProgressDialog pDialog;
    TinyDB tinyDB;
    private TextView txtHarga, txtJumlahProduk, txtDeskripsi, txtNamaProduk, txtPenjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_qc);
        NumberFormat formatter = new DecimalFormat("#0,000");

        txtNamaProduk = (TextView) findViewById(R.id.nama_produk);
        txtPenjual = (TextView) findViewById(R.id.nama_penjual);
        txtHarga = (TextView) findViewById(R.id.harga);
        txtJumlahProduk = (TextView) findViewById(R.id.stok);
        txtDeskripsi = (TextView) findViewById(R.id.deskripsi);

        et_catatan = (EditText) findViewById(R.id.et_catatan);
        btnTolak = findViewById(R.id.btnTolak);
        btnRevisi = findViewById(R.id.btnRevisi);
        btnTerima = findViewById(R.id.btnTerima);
        gambar = (NetworkImageView) findViewById(R.id.gambar);

        idProduk = getIntent().getStringExtra("id_produk");
        namaProduk = getIntent().getStringExtra("nama_produk");
        deskripsi = getIntent().getStringExtra("deskripsi");
        harga = getIntent().getStringExtra("harga");
        foto_produk = getIntent().getStringExtra("foto_produk");
        stok = getIntent().getStringExtra("stok");
        penjual = getIntent().getStringExtra("penjual");
        foto_produk = getIntent().getStringExtra("foto_produk");
        catatan=getIntent().getStringExtra("catatan");



        String price = formatter.format(Double.parseDouble(harga));

        txtNamaProduk.setText(namaProduk);
        txtPenjual.setText("penjual : " + penjual);
        txtHarga.setText("Rp." + price);
        txtJumlahProduk.setText("Stok : " + stok);
        txtDeskripsi.setText(deskripsi);
        gambar.setImageUrl(foto_produk, imageLoader);
        et_catatan.setText(catatan);


        tinyDB = new TinyDB(adminDetailQc.this);

        btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubahStatus("ditolak");

            }
        });

        btnRevisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubahStatus("revisi");

            }
        });

        btnTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubahStatus("diterima");
            }
        });
    }

    public void ubahStatus(final String action) {


        catatan=et_catatan.getText().toString().trim();

        /*System.out.println(id_user);
        System.out.println(token);
        System.out.println(jumlah);
        System.out.println(idProduk);*/

        id_user = tinyDB.getString("id_user");
        token = tinyDB.getString("token");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.ubahStatusProductAdminURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                //JSONObject user = jObj.getJSONObject("user");
                                Toast.makeText(getApplicationContext(), "ubah status sukses", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(adminDetailQc.this, admin.class);
                                startActivity(intent);

                                // onResume();


                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(adminDetailQc.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_user", id_user);
                map.put("token",token);
                map.put("id_produk",idProduk);
                map.put("action",action);
                map.put("catatan",catatan);


                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);


    }
}
