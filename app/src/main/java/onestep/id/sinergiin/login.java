package onestep.id.sinergiin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import onestep.id.sinergiin.Admin.admin;
import onestep.id.sinergiin.Model.BaseApi;
import onestep.id.sinergiin.Pembeli.pembeli;
import onestep.id.sinergiin.Pengrajin.Pengrajin;

public class login extends AppCompatActivity {
    private Button login,regis;
    private EditText txtUsername, txtPassword;
    private String username, password,id_user,token,email,role;
    private ProgressDialog pDialog;

    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        tinyDB = new TinyDB(getApplicationContext());

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setLogin();
                userLogin();
            }
        });
        regis = (Button) findViewById(R.id.btn_register);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,register.class);
                startActivity(i);
            }
        });
    }

   /* private void setLogin() {
        email = txtEmail.getText().toString().trim();
        pass = txtPassword.getText().toString().trim();
        tinyDB.putString("id_user","14");
        tinyDB.putString("token","2b6898a282eece7bae4cdb706d4dcb1203433eee69d7ab317eaa081737ee5632");

        if (email.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")) {
            Intent i = new Intent(login.this, admin.class);
            startActivity(i);
            finish();
        } else if (email.equalsIgnoreCase("pengrajin") && pass.equalsIgnoreCase("pengrajin")) {
            Intent i = new Intent(login.this, Pengrajin.class);
            startActivity(i);
            finish();
        } else if (email.equalsIgnoreCase("pembeli") && pass.equalsIgnoreCase("pembeli")) {
            Intent i = new Intent(login.this, pembeli.class);
            startActivity(i);
            finish();
        }
    }
*/
    private void userLogin() {
        username = txtUsername.getText().toString().trim();
        password = txtPassword.getText().toString().trim();
        if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
            Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_LONG).show();
        } else {
            pDialog = new ProgressDialog(this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");
            pDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.loginURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jObj = new JSONObject(response);
                                boolean error = jObj.getBoolean("error");
                                if (!error) {

                                    //JSONObject user = jObj.getJSONObject("user");
                                    id_user = jObj.getString("id_user");
                                    username = jObj.getString("username").trim();
                                    email = jObj.getString("email").trim();
                                    role = jObj.getString("role").trim();
                                    token = jObj.getString("token").trim();


                                    tinyDB.putString("id_user", id_user); // Storing string
                                    tinyDB.putString("username", username);
                                    tinyDB.putString("email", email);
                                    tinyDB.putString("token", token);

                                    if (role.equalsIgnoreCase("pembeli")){
                                        Intent intent = new Intent(login.this, pembeli.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else if(role.equalsIgnoreCase("pengrajin")){
                                        Intent intent = new Intent(login.this, Pengrajin.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if(role.equalsIgnoreCase("admin")){
                                        Intent intent = new Intent(login.this, admin.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {

                                    }





                                } else {
                                    // Error in login. Get the error message
                                    String errorMsg = jObj.getString("error_msg");
                                    Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                                    hidePDialog();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(login.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("username", username);
                    map.put("password", password);
                    return map;
                }
            };
            AppController.getInstance().addToRequestQueue(stringRequest);
        }
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
