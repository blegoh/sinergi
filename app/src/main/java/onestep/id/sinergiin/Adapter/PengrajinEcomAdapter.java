package onestep.id.sinergiin.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.mPengrajinEcom;
import onestep.id.sinergiin.Pembeli.pembeliDetailCommerce;
import onestep.id.sinergiin.Pengrajin.PengrajinDetailEcom;
import onestep.id.sinergiin.R;

public class PengrajinEcomAdapter extends RecyclerView.Adapter<PengrajinEcomAdapter.ViewHolder> {
    private Activity activity;
    private List<mPengrajinEcom> list;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public PengrajinEcomAdapter(Activity activity, List<mPengrajinEcom> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public PengrajinEcomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pengrajin_ecom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PengrajinEcomAdapter.ViewHolder holder, int position) {
        final mPengrajinEcom m = list.get(position);
        NumberFormat formatter = new DecimalFormat("#0,000");
        holder.txtTitle.setText(m.getNamaProduk());
        String price = formatter.format(Double.parseDouble(m.getHarga())).replace(",000","k");
        holder.txtPrice.setText("Rp."+price);
        holder.img.setImageUrl(m.getThumbnailUrl(),imageLoader);
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PengrajinDetailEcom.class);
                intent.putExtra("harga", m.getHarga());
                intent.putExtra("nama_produk", m.getNamaProduk());
                intent.putExtra("deskripsi", m.getDeskripsi());
                intent.putExtra("foto_produk", m.getThumbnailUrl());
                intent.putExtra("id_produk", m.getId());
                intent.putExtra("stok", m.getJumlahStok());
                intent.putExtra("penjual",m.getNamaPenjual());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtPrice;
        NetworkImageView img;
        Button buy;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.ecomTitle);
            txtPrice = (TextView) itemView.findViewById(R.id.ecomPrice);
            img = (NetworkImageView) itemView.findViewById(R.id.ecomImage);
            buy = (Button) itemView.findViewById(R.id.btnBuy);
        }
    }
}
