package onestep.id.sinergiin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

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
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_pengrajin_pembelian, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView img = (NetworkImageView) view.findViewById(R.id.iv_pembeli);
        TextView txtPembeli = (TextView) view.findViewById(R.id.tv_pembeli);
        TextView txtProduk = (TextView) view.findViewById(R.id.tv_produkPembeli);
        TextView txtJumlah = (TextView) view.findViewById(R.id.tv_pcsPembeli);
        TextView txtDurasi = (TextView) view.findViewById(R.id.tv_durasi);

        txtPembeli.setText(m.getPembeli());
        txtJumlah.setText(m.getJumlah());
        txtProduk.setText(m.getProduk());
        txtDurasi.setText(m.getDurasi());
        img.setImageUrl(m.getImg(), imageLoader);
        view.setTag(m.getId());
        return view;
    }
}
