package onestep.id.sinergiin.Pembeli;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.MainActivity;
import onestep.id.sinergiin.Model.BaseApi;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;

public class pembeliDetailCommerce extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private NetworkImageView gambar;

    private String idProduk, namaProduk, deskripsi, harga, foto_produk, stok, penjual, jumlah;
    private TextView txtHarga, txtJumlahProduk, txtDeskripsi, txtNamaProduk, txtPenjual;
    private EditText etJumlah;
    private Button btnCart, btnBeli;

    ArrayList<String> listIdProduk = new ArrayList<>();
    ArrayList<String> listJumlahPembelian = new ArrayList<>();
    TinyDB tinyDB;

    private double totalHarga;
    private String id_user, token;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli_detail_commerce);
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
        listIdProduk = tinyDB.getListString("id_produk");
        listJumlahPembelian=tinyDB.getListString("jumlah");

        id_user = tinyDB.getString("id_user");
        token = tinyDB.getString("token");

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beli();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart();
            }
        });



    }

    public void beli() {


       jumlah=etJumlah.getText().toString().trim();

        /*System.out.println(id_user);
        System.out.println(token);
        System.out.println(jumlah);
        System.out.println(idProduk);*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.insertBeli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                //JSONObject user = jObj.getJSONObject("user");
                                Toast.makeText(getApplicationContext(), "pembelian sukses", Toast.LENGTH_SHORT).show();
                                Intent  intent = new Intent(pembeliDetailCommerce.this, pembeli.class);
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
                        Toast.makeText(pembeliDetailCommerce.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_user", id_user);
                map.put("token",token);
                map.put("id_produk[0]",idProduk);
                map.put("jumlah[0]",jumlah);
                /*for (int i = 0; i < tinyDB.getListString("idProduk").size(); i++) {
                    map.put("idProduk[" + i + "]", tinyDB.getListString("idProduk").get(i).toString());
                    map.put("jumlah[" + i + "]", tinyDB.getListString("jumlah").get(i).toString());
                }*/


                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);


    }

    public void cart() {
        listIdProduk.add(idProduk);
        listJumlahPembelian.add(etJumlah.getText().toString());

        tinyDB.putListString("id_produk", listIdProduk);
        tinyDB.putListString("jumlah", listJumlahPembelian);

        Toast.makeText(pembeliDetailCommerce.this, namaProduk+" berhasil ditambahkan ke cart", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(pembeliDetailCommerce.this,pembeli.class);
        startActivity(intent);


    }
}
