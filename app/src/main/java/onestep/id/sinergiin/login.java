package onestep.id.sinergiin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    private Button login;
    private EditText txtEmail, txtPassword;
    private String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();
            }
        });
    }

    private void setLogin() {
        email = txtEmail.getText().toString().trim();
        pass = txtPassword.getText().toString().trim();
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
