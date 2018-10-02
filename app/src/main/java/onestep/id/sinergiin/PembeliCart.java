package onestep.id.sinergiin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import onestep.id.sinergiin.Adapter.PembeliCartAdapter;
import onestep.id.sinergiin.Model.BaseApi;
import onestep.id.sinergiin.Model.mPembeliCart;
import onestep.id.sinergiin.Model.mPembeliEcom;

public class PembeliCart extends AppCompatActivity {
    private ListView listView;
    private PembeliCartAdapter adapter;
    private List<mPembeliCart> list = new ArrayList<>();

    private String id_user, token;
    private ProgressDialog pDialog;

    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli_cart);
        listView = (ListView) findViewById(R.id.lv_cart);
   /*     list.add(new mPembeliCart("1", "Lampu bambu", "Eldi", "10", "Rp.10.000", ""));
        list.add(new mPembeliCart("2", "Kayu", "Sofyan", "10", "Rp.20.000", ""));*/


        tinyDB = new TinyDB(getApplicationContext());


        adapter = new PembeliCartAdapter(this, list);
        listView.setAdapter(adapter);
        getCart();
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
                            }
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
