package onestep.id.sinergiin.Pengrajin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.CustomNetworkImageView;
import onestep.id.sinergiin.Model.BaseApi;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;

public class pengrajinJualProduk extends AppCompatActivity {
    private Button btnJual;
    private Toolbar toolbar;
    private ImageButton browseBtn;
    private CustomNetworkImageView imgProduct;
    private EditText txtNamaProduk,txtHarga,txtDeskripsi,txtStok;
    private Bitmap bitmap;
    private String id_user,token,idProduk,gambar,namaProduk,harga,deskripsi,stok;
    private ProgressDialog pDialog;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengrajin_jual_produk);
        tinyDB = new TinyDB(getApplicationContext());

        txtNamaProduk = (EditText)findViewById(R.id.txt_produk);
        txtHarga=(EditText)findViewById(R.id.txt_hargaProduk);
        txtStok=(EditText)findViewById(R.id.txt_jumlah);
        txtDeskripsi=(EditText)findViewById(R.id.txt_deskripsi);

        btnJual = (Button)findViewById(R.id.btn_jual);
        browseBtn=(ImageButton)findViewById(R.id.browse);
        imgProduct=(CustomNetworkImageView)findViewById(R.id.image);


        browseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pilihFoto();
            }
        });

        btnJual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProduk();
            }
        });

    }

    public void onBackPressed(){
        super.onBackPressed();
        Intent i=new Intent(Intent.ACTION_MAIN);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }

    private void pilihFoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgProduct.setLocalImageBitmap(bitmap);
                browseBtn.setBackground(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    private void uploadProduk() {
        pDialog = new ProgressDialog(pengrajinJualProduk.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
               hidePDialog();
               onBackPressed();
            }
        }, 3000);

        id_user=tinyDB.getString("id_user");
        token=tinyDB.getString("token");


        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.insertProductURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("status");
                            if (status) {
                                //JSONObject user = jObj.getJSONObject("user");
                                Toast.makeText(getApplicationContext(), "upload sukses", Toast.LENGTH_SHORT).show();
                                //onBackPressed();

                                Intent intent =new Intent(pengrajinJualProduk.this,Pengrajin.class);
                                startActivity(intent);


                                // onResume();


                            } else {
                                // Error in login. Get the error message
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                                hidePDialog();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + token+" "+id_user,
                                    Toast.LENGTH_LONG).show();
                            hidePDialog();
                        }

                    }




                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(pengrajinJualProduk.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id_user",id_user);
                map.put("token",token);


                map.put("nama_produk", txtNamaProduk.getText().toString().trim());
                map.put("harga", txtHarga.getText().toString().trim());
                map.put("stok",txtStok.getText().toString().trim());
                map.put("deskripsi", txtDeskripsi.getText().toString().trim());

                map.put("gambar", imageToString(bitmap));
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

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
