package onestep.id.sinergiin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import onestep.id.sinergiin.Admin.admin;
import onestep.id.sinergiin.Pembeli.pembeli;
import onestep.id.sinergiin.Pengrajin.Pengrajin;

public class login extends AppCompatActivity {
    private Button login,regis;
    private EditText txtEmail, txtPassword;
    private String email, pass;

    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        tinyDB = new TinyDB(getApplicationContext());

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();
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

    private void setLogin() {
        email = txtEmail.getText().toString().trim();
        pass = txtPassword.getText().toString().trim();
        tinyDB.putString("id_user","13");
        tinyDB.putString("token","2b6898a282eece7bae4cdb706d4dcb1203433eee69d7ab317eaa081737ee5636");

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
}
