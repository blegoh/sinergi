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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.mPengrajinMarket;
import onestep.id.sinergiin.R;

public class PengrajinMarketAdapter extends BaseAdapter {
    private Activity activity;
    private List<mPengrajinMarket> list;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public PengrajinMarketAdapter(Activity activity, List<mPengrajinMarket> list) {
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
        View view = View.inflate(activity, R.layout.list_pengrajin_market,null);
        mPengrajinMarket m = list.get(position);
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_pengrajin_market, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NumberFormat formatter = new DecimalFormat("#0,000");
        String price = formatter.format(Double.parseDouble(m.getHarga()));

        NetworkImageView img = (NetworkImageView) view.findViewById(R.id.iv_produk);
        TextView txtTitle = (TextView) view.findViewById(R.id.tv_produk);
        TextView txtPcs = (TextView) view.findViewById(R.id.tv_produkPcs);
        TextView txtDesc = (TextView) view.findViewById(R.id.tv_keterangan);

        txtTitle.setText(m.getJudul());
        txtPcs.setText("Rp."+price);
        txtDesc.setText(m.getEcommerce());
        img.setImageUrl(m.getGambar(), imageLoader);
        view.setTag(m.getId());
        return view;
    }
}
