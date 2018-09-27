package onestep.id.sinergiin.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import onestep.id.sinergiin.Model.mPembeliPesanan;
import onestep.id.sinergiin.R;

public class PembeliPesananAdapter extends BaseAdapter {
    private Activity activity;
    private List<mPembeliPesanan> list;

    public PembeliPesananAdapter(Activity activity, List<mPembeliPesanan> list) {
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
        View view = View.inflate(activity, R.layout.list_pembeli_pesanan, null);
        mPembeliPesanan m = list.get(position);

        TextView txtProduk = (TextView) view.findViewById(R.id.tv_produk);
        TextView txtJumlah = (TextView) view.findViewById(R.id.tv_jumlah);
        TextView txtEstimasi = (TextView) view.findViewById(R.id.tv_estimasi);
        TextView txtVerif = (TextView) view.findViewById(R.id.tv_verif);

        txtProduk.setText(m.getProduk());
        txtJumlah.setText("Jumlah : " + m.getJumlah());
        txtEstimasi.setText("Estimasi : " + m.getEstimasi()+" Hari");
        txtVerif.setText("Status : " + m.getVerif());
        view.setTag(m.getId());
        return view;
    }
}
