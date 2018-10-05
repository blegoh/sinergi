package onestep.id.sinergiin.Pengrajin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;

public class pengrajinDetailPesanan extends AppCompatActivity {
    ArrayList<String> list_status = new ArrayList<>();
    String status;
    ImageView iv1, iv2, iv3, iv4;
    Button btn_konfirm;

    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengrajin_detail_pesanan);
        tinyDB = new TinyDB(pengrajinDetailPesanan.this);


        iv1 = (ImageView) findViewById(R.id.icon_diterima);
        iv2 = (ImageView) findViewById(R.id.icon_dikerjakan);
        iv3 = (ImageView) findViewById(R.id.icon_pengiriman);
        iv4 = (ImageView) findViewById(R.id.icon_sukses);
        status = getIntent().getStringExtra("status");

        btn_konfirm = (Button) findViewById(R.id.btn_konfirmasi);
        btn_konfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (status){
                    case "diterima":
                        status = "dikerjakan";
                        list_status.add(status);
                        tinyDB.putListString("status_pengrajin",list_status);
                        setIcon(status);
                        break;
                    case "dikerjakan":
                        status = "dikirim";
                        list_status.add(status);
                        tinyDB.putListString("status_pengrajin",list_status);
                        setIcon(status);
                        break;
                    case "dikirim":
                        status = "sukses";
                        list_status.add(status);
                        tinyDB.putListString("status_pengrajin",list_status);
                        setIcon(status);
                        break;
                    default:
                        break;
                }
            }
        });

        iv1.setImageResource(R.drawable.ic_diterima_grey);
        iv2.setImageResource(R.drawable.ic_dikerjakan_grey);
        iv3.setImageResource(R.drawable.ic_dikirim_grey);
        iv4.setImageResource(R.drawable.ic_check_circle_grey_24dp);
        setIcon(status);

    }

    public void setIcon(String status){
        switch (status){
            case "diterima":
                iv1.setImageResource(R.drawable.ic_diterima_green);
                break;
            case "dikerjakan":
                iv1.setImageResource(R.drawable.ic_diterima_green);
                iv2.setImageResource(R.drawable.ic_dikerjakan_green);
                break;
            case "dikirim":
                iv1.setImageResource(R.drawable.ic_diterima_green);
                iv2.setImageResource(R.drawable.ic_dikerjakan_green);
                iv3.setImageResource(R.drawable.ic_dikirim_green);
                break;
            case "sukses":
                iv1.setImageResource(R.drawable.ic_diterima_green);
                iv2.setImageResource(R.drawable.ic_dikerjakan_green);
                iv3.setImageResource(R.drawable.ic_dikirim_green);
                iv4.setImageResource(R.drawable.ic_check_circle_black_24dp);
                break;
            default:
                break;
        }


    }

}
