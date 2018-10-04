package onestep.id.sinergiin.Admin;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import onestep.id.sinergiin.Adapter.AdminMarketAdpater;
import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.BaseApi;
import onestep.id.sinergiin.Model.mAdminEcom;
import onestep.id.sinergiin.Model.mAdminMarket;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminMarket extends Fragment {
    private ListView listView;
    private AdminMarketAdpater adapter;
    private List<mAdminMarket> list = new ArrayList<>();
    private ProgressDialog pDialog;
    private String id_user,token;
    private Button btnSearch;
    private EditText et_cari;
    TinyDB tinyDB;

    public adminMarket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_market, container, false);
        listView = (ListView) view.findViewById(R.id.lv_adminMarket);
       /* list.add(new mAdminMarket("1","","Lampu Bambu","3500","Produk ini blablabla"));
        list.add(new mAdminMarket("2","","Kerajinan Bambu","3000","Produk ini blablabla"));*/
        btnSearch=view.findViewById(R.id.btnSearch);
        et_cari=view.findViewById(R.id.cari);

        tinyDB = new TinyDB(getActivity());
        adapter = new AdminMarketAdpater(getActivity(),list);
        listView.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMarket(et_cari.getText().toString().trim());
            }
        });
        return view;
    }

    public void getMarket(final String keyword){
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        list.clear();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                hidePDialog();
                //onBackPressed();
            }
        }, 4000);

        id_user = tinyDB.getString("id_user");
        token = tinyDB.getString("token");

        // Creating volley request obj
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.getMarketTrendURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(String.valueOf(getActivity()), response.toString());
                        hidePDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject objProduk = jsonArray.getJSONObject(i);
                                final mAdminMarket produk = new mAdminMarket();
                                //produk.setId(objProduk.getString("id_produk"));
                                produk.setJudul(objProduk.getString("judul"));
                                produk.setGambar(objProduk.getString("gambar"));
                                produk.setHarga(objProduk.getString("harga"));
                                produk.setEcommerce(objProduk.getString("e-commerce"));

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
                VolleyLog.d(String.valueOf(getActivity()), "Error: " + error.getMessage());
                hidePDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_user", id_user);
                map.put("token", token);
                map.put("keyword",keyword);
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
