package onestep.id.sinergiin.Admin;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import onestep.id.sinergiin.Adapter.AdminEcomAdapter;
import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.BaseApi;
import onestep.id.sinergiin.Model.mAdminEcom;
import onestep.id.sinergiin.Model.mPengrajinEcom;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminEcommerce extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<mAdminEcom> list = new ArrayList<>();
    private ProgressDialog pDialog;
    private String id_user,token;
    TinyDB tinyDB;


    public adminEcommerce() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_ecommerce, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_adminCommerce);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
   /*     list.add(new mAdminEcom("1","","Kayu","10.000"));
        list.add(new mAdminEcom("2","","Bambu","20.000"));*/

        tinyDB = new TinyDB(getActivity());
        adapter = new AdminEcomAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        getProduct();
        return view;
    }

    public void getProduct(){
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        list.clear();


        id_user = tinyDB.getString("id_user");
        token = tinyDB.getString("token");

        // Creating volley request obj
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.getAllProductPembeliURL,
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
                                final mAdminEcom produk = new mAdminEcom();
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
                VolleyLog.d(String.valueOf(getActivity()), "Error: " + error.getMessage());
                hidePDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_user", id_user);
                map.put("token", token);
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
