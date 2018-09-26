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
import onestep.id.sinergiin.Model.mPengrajinQc;
import onestep.id.sinergiin.R;

public class PengrajinQcAdapter extends BaseAdapter {
    private Activity activity;
    private List<mPengrajinQc> list;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public PengrajinQcAdapter(Activity activity, List<mPengrajinQc> list) {
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
        View view = View.inflate(activity, R.layout.list_pengrajin_qc, null);
        mPengrajinQc m = list.get(position);
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_pengrajin_market, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView qcImage = (NetworkImageView) view.findViewById(R.id.qcImage);
        TextView txtTitle = (TextView) view.findViewById(R.id.qcTitle);
        TextView txtDate = (TextView) view.findViewById(R.id.qcDate);
        ImageView qcVerif = (ImageView) view.findViewById(R.id.qcVerif);

        qcImage.setImageUrl(m.getImg(), imageLoader);
        txtTitle.setText(m.getTitle());
        txtDate.setText(m.getDate());
        qcVerif.setImageResource(m.getVerif());
        view.setTag(m.getId());
        return view;
    }
}
