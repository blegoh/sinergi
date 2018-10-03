package onestep.id.sinergiin.Pembeli;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import onestep.id.sinergiin.Adapter.PembeliCartAdapter;
import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.BaseApi;
import onestep.id.sinergiin.Model.mPembeliCart;
import onestep.id.sinergiin.Model.mPembeliEcom;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;

public class PembeliCart extends AppCompatActivity {
    private ListView listView;
    private PembeliCartAdapter adapter;
    private List<mPembeliCart> list = new ArrayList<>();
    private List<Double> totalHarga = new ArrayList<>();
    private double harga = 0;
    private TextView txtTotalHarga;
    private Button btnBayar;

    private String id_user, token;
    private ProgressDialog pDialog;

    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli_cart);
        listView = (ListView) findViewById(R.id.lv_cart);
        txtTotalHarga = (TextView) findViewById(R.id.totalHarga);
   /*     list.add(new mPembeliCart("1", "Lampu bambu", "Eldi", "10", "Rp.10.000", ""));
        list.add(new mPembeliCart("2", "Kayu", "Sofyan", "10", "Rp.20.000", ""));*/


        tinyDB = new TinyDB(getApplicationContext());
        btnBayar = (Button) findViewById(R.id.btnBayar);


        adapter = new PembeliCartAdapter(this, list);
        listView.setAdapter(adapter);
        getCart();
        //setTotalHarga();

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert_pembelian();


            }
        });
    }

    public void getCart() {
       /* System.out.println(id_user);
        System.out.println(token);

        for (int i =0;i<tinyDB.getListString("id_produk").size();i++){
            System.out.println(tinyDB.getListString("id_produk"));
        }*/
       /* pDialog = new ProgressDialog(getApplicationContext());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();*/
        Locale localeID = new Locale("in", "ID");
        final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        list.clear();


        id_user = tinyDB.getString("id_user");
        token = tinyDB.getString("token");

        // Creating volley request obj
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.showCart,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(String.valueOf(getApplicationContext()), response.toString());
                        hidePDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject objProduk = jsonArray.getJSONObject(i);
                                final mPembeliCart produk = new mPembeliCart();
                                produk.setId(objProduk.getString("id_produk"));
                                produk.setNamaProduk(objProduk.getString("nama_produk"));
                                produk.setThumbnailUrl(BaseApi.imageURL + objProduk.getString("foto_produk"));
                                produk.setHarga(objProduk.getString("harga"));
                                produk.setJumlahStok(objProduk.getString("jumlah_stok"));
                                produk.setDeskripsi(objProduk.getString("deskripsi"));
                                produk.setNamaPenjual(objProduk.getString("penjual"));
                                list.add(produk);

                                //System.out.println(objProduk.);
                                double harga2 = Double.parseDouble(tinyDB.getListString("jumlah").get(i).toString().trim()) * Double.parseDouble(objProduk.getString("harga").toString().trim());
                                totalHarga.add(harga2);
                            }

                            for (int i = 0; i < totalHarga.size(); i++) {
                                harga += totalHarga.get(i);
                            }

                            txtTotalHarga.setText(formatRupiah.format(harga));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(String.valueOf(getApplicationContext()), "Error: " + error.getMessage());
                hidePDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_user", id_user);
                map.put("token", token);

                for (int i = 0; i < tinyDB.getListString("id_produk").size(); i++) {
                    map.put("id_produk[" + i + "]", tinyDB.getListString("id_produk").get(i).toString());
                    map.put("jumlah[" + i + "]", tinyDB.getListString("jumlah").get(i).toString());
                }
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public  void insert_pembelian() {

        id_user = tinyDB.getString("id_user");
        token = tinyDB.getString("token");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.insertBeli,
                new Response.Listener<String>() {
                    @Override
                    public  void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                //JSONObject user = jObj.getJSONObject("user");
                                Toast.makeText(getApplicationContext(), "pembelian sukses", Toast.LENGTH_SHORT).show();
                                //onBackPressed();
                                tinyDB.remove("id_produk");
                                tinyDB.remove("jumlah");
                                Intent intent = new Intent(PembeliCart.this, pembeli.class);
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
                        Toast.makeText(PembeliCart.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_user", id_user);
                map.put("token", token);

                for (int i = 0; i < tinyDB.getListString("id_produk").size(); i++) {
                    map.put("id_produk[" + i + "]", tinyDB.getListString("id_produk").get(i).toString());
                    map.put("jumlah[" + i + "]", tinyDB.getListString("jumlah").get(i).toString());
                }


                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}




