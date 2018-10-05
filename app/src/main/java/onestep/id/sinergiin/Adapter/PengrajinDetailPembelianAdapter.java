package onestep.id.sinergiin.Adapter;

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
import onestep.id.sinergiin.Model.mPengrajinDetailPembelian;
import onestep.id.sinergiin.R;

/**
 * Created by altintop on 05/10/18.
 */

public class PengrajinDetailPembelianAdapter extends BaseAdapter {
  private Context context;
  private List<mPengrajinDetailPembelian> list;
  private LayoutInflater inflater;
  ImageLoader imageLoader = AppController.getInstance().getImageLoader();

  public PengrajinDetailPembelianAdapter(Context context, List<mPengrajinDetailPembelian> list) {
    this.context = context;
    this.list = list;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Object getItem(int i) {
    return list.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    View v = View.inflate(context, R.layout.list_pengrajin_detail_pembelian, null);
    mPengrajinDetailPembelian m = list.get(i);
    if (inflater == null)
      inflater = (LayoutInflater) context
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (view == null)
      view = inflater.inflate(R.layout.list_pengrajin_detail_pembelian, null);
    if (imageLoader == null)
      imageLoader = AppController.getInstance().getImageLoader();

    NetworkImageView img = (NetworkImageView) v.findViewById(R.id.iv_pembeli);
    TextView txtBarang = (TextView) v.findViewById(R.id.tv_produkPembeli);
    TextView txtHarga = (TextView) v.findViewById(R.id.tv_harga);
    TextView txtJumlah = (TextView) v.findViewById(R.id.tv_pcsPembeli);

    img.setImageUrl(m.getImg(),imageLoader);
    txtBarang.setText(m.getBarang());
    txtHarga.setText("Rp."+m.getHarga());
    txtJumlah.setText("Qty: "+m.getJumlah());
    v.setTag(m.getId());
    return v;
  }
}
