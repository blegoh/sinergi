package onestep.id.sinergiin.Pengrajin;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import onestep.id.sinergiin.Adapter.PengrajinQcAdapter;
import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.BaseApi;
import onestep.id.sinergiin.Model.mPembeliEcom;
import onestep.id.sinergiin.Model.mPengrajinQc;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengrajin3Fragment extends Fragment {
    private ListView listView;
    private PengrajinQcAdapter adapter;
    private List<mPengrajinQc> list = new ArrayList<>();
    private String id_user, token;
    private ProgressDialog pDialog;
    TinyDB tinyDB;

    public Pengrajin3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengrajin3, container, false);
        listView = (ListView) view.findViewById(R.id.listPengrajinQc);
        tinyDB=new TinyDB(getActivity());

        /*list.add(new mPengrajinQc("1", "", "Lampu Bambu", "5 Oktober 2018", R.drawable.ic_check_circle_black_24dp,"Revisi"));
        list.add(new mPengrajinQc("2", "", "Kerajinan Bambu", "10 Oktober", R.drawable.ic_check_circle_grey_24dp,"Diterima"));*/
        adapter = new PengrajinQcAdapter(getActivity(), list);
        listView.setAdapter(adapter);
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.getAllProductPengrajinURL,
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
                                final mPengrajinQc produk = new mPengrajinQc();
                                produk.setId(objProduk.getString("id_produk"));
                                produk.setNamaProduk(objProduk.getString("nama_produk"));
                                produk.setThumbnailUrl(BaseApi.imageURL + objProduk.getString("foto_produk"));
                                produk.setHarga(objProduk.getString("harga"));
                                produk.setJumlahStok(objProduk.getString("jumlah_stok"));
                                produk.setDeskripsi(objProduk.getString("deskripsi"));
                                produk.setNamaPenjual(objProduk.getString("penjual"));
                                produk.setStatus(objProduk.getString("status"));
                                produk.setCatatan(objProduk.getString("catatan"));
                                if (objProduk.getString("status").equalsIgnoreCase("diterima")){
                                    produk.setVerif(R.drawable.ic_check_circle_black_24dp);
                                }
                                else if (objProduk.getString("status").equalsIgnoreCase("revisi")){
                                    produk.setVerif(R.drawable.ic_restore_yellow_24dp);
                                }
                                else if (objProduk.getString("status").equalsIgnoreCase("ditolak")){
                                    produk.setVerif(R.drawable.ic_block_red_24dp);

                                }
                                else {
                                    produk.setVerif(R.drawable.ic_watch_later_grey_24dp);
                                }
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
