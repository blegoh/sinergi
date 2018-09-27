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

import java.util.List;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.mAdminEcom;
import onestep.id.sinergiin.R;
import onestep.id.sinergiin.adminDetailProduk;

public class AdminEcomAdapter extends RecyclerView.Adapter<AdminEcomAdapter.ViewHolder> {
    private Activity activity;
    private List<mAdminEcom> list;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public AdminEcomAdapter(Activity activity, List<mAdminEcom> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_admin_ecommerce,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final mAdminEcom m = list.get(position);
        holder.txtTitle.setText(m.getTitle());
        holder.txtPrice.setText(m.getPrice());
        holder.img.setImageUrl(m.getImg(),imageLoader);
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity,adminDetailProduk.class);
                activity.startActivity(i);
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
            txtPrice = (TextView) itemView.findViewById(R.id.tv_item_harga);
            img = (NetworkImageView) itemView.findViewById(R.id.iv_produk);
            buy = (Button) itemView.findViewById(R.id.btn_beli);
        }
    }
}
