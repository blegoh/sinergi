package onestep.id.sinergiin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.mPengrajinPembelian;
import onestep.id.sinergiin.R;

public class PengrajinPembelianAdapter extends BaseAdapter {
    private Activity activity;
    private List<mPengrajinPembelian> list;

    public PengrajinPembelianAdapter(Activity activity, List<mPengrajinPembelian> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.list_pengrajin_pembelian,null);
        mPengrajinPembelian m = list.get(position);

        ImageView img = (ImageView) view.findViewById(R.id.iv_verif);
        TextView txtTransaksi = (TextView) view.findViewById(R.id.tv_transaksi);
        TextView txtPembeli = (TextView) view.findViewById(R.id.tv_Pembeli);
        TextView txtTotal = (TextView) view.findViewById(R.id.tv_totalHarga);

        txtPembeli.setText("Pembeli : "+m.getPembeli());
        txtTransaksi.setText("No Traksaksi : "+m.getNotrans());
        txtTotal.setText("Total Harga : "+m.getJumlah());
        img.setImageResource(Integer.parseInt(m.getImg()));
        view.setTag(m.getId());
        return view;
    }
}
