package onestep.id.sinergiin.Pengrajin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import onestep.id.sinergiin.R;

public class PengrajinDetailEcom extends AppCompatActivity {
    Button buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengrajin_detail_ecom);
        buy = (Button) findViewById(R.id.btn_beli);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
