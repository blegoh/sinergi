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
import onestep.id.sinergiin.Model.mAdminPembagian;
import onestep.id.sinergiin.R;

public class AdminPembagianAdapter extends BaseAdapter {
    private Activity activity;
    private List<mAdminPembagian> list;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public AdminPembagianAdapter(Activity activity, List<mAdminPembagian> list) {
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
        View view = View.inflate(activity, R.layout.list_admin_pembagian,null);
        mAdminPembagian m = list.get(position);
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_admin_pembagian, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView img = (NetworkImageView) view.findViewById(R.id.iv_pembeli);
        TextView txtPembeli = (TextView) view.findViewById(R.id.tv_pembeli);
        TextView txtProduk = (TextView) view.findViewById(R.id.tv_produkPembeli);
        TextView txtPcs = (TextView) view.findViewById(R.id.tv_pcsPembeli);
        TextView txtDate = (TextView) view.findViewById(R.id.tv_durasi);

        txtPembeli.setText(m.getPembeli());
        txtProduk.setText(m.getProduk());
        txtDate.setText(m.getDate());
        txtPcs.setText(m.getPcs() + " pcs");
        img.setImageUrl(m.getImg(), imageLoader);
        view.setTag(m.getId());
        return view;
    }
}
