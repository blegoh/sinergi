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

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.mPembeliCart;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.TinyDB;

public class PembeliCartAdapter extends BaseAdapter {
    private Context context;
    private List<mPembeliCart> list;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    TinyDB tinyDB;

    public PembeliCartAdapter(Context context, List<mPembeliCart> list) {
        this.context = context;
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
        View view = View.inflate(context, R.layout.list_pembeli_cart, null);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        tinyDB = new TinyDB(context);

        mPembeliCart m = list.get(position);
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_pembeli_pembelian, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView img = (NetworkImageView) view.findViewById(R.id.iv_pembeli);
        TextView txtPenjual = (TextView) view.findViewById(R.id.tv_penjual);
        TextView txtProduk = (TextView) view.findViewById(R.id.tv_produkPembeli);
        TextView txtJumlah = (TextView) view.findViewById(R.id.tv_pcsPembeli);
        TextView txtHarga = (TextView) view.findViewById(R.id.tv_harga);

        String jumlah = tinyDB.getListString("jumlah").get(position).toString();
        img.setImageUrl(m.getThumbnailUrl(), imageLoader);
        txtPenjual.setText(m.getNamaPenjual());
        txtProduk.setText(m.getNamaProduk());
        txtJumlah.setText("jumlah beli : "+jumlah);
        txtHarga.setText(formatRupiah.format(Double.parseDouble(m.getHarga()))+"/pcs");
        view.setTag(m.getId());
        return view;
    }
}
