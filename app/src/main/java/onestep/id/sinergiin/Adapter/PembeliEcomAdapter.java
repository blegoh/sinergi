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
import java.util.Locale;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.mPembeliEcom;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.Pembeli.pembeliDetailCommerce;

public class PembeliEcomAdapter extends RecyclerView.Adapter<PembeliEcomAdapter.ViewHolder>  {
    private Activity activity;
    private List<mPembeliEcom> list;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public PembeliEcomAdapter(Activity activity, List<mPembeliEcom> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pembeli_commerce,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Locale localeID = new Locale("in", "ID");
        //NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        NumberFormat formatter = new DecimalFormat("#0,000");

        final mPembeliEcom m = list.get(position);
        holder.txtTitle.setText(m.getNamaProduk());
        String price = formatter.format(Double.parseDouble(m.getHarga())).replace(",000","k");
        holder.txtPrice.setText("Rp."+price);
        holder.img.setImageUrl(m.getThumbnailUrl(),imageLoader);
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, pembeliDetailCommerce.class);
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
            txtTitle = (TextView) itemView.findViewById(R.id.tv_produk);
            txtPrice = (TextView) itemView.findViewById(R.id.tv_hargaProduk);
            img = (NetworkImageView) itemView.findViewById(R.id.iv_produk);
            buy = (Button) itemView.findViewById(R.id.btn_beli);
        }
    }
}
