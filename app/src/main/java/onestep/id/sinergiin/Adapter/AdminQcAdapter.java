package onestep.id.sinergiin.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import onestep.id.sinergiin.AppController;
import onestep.id.sinergiin.Model.mAdminQc;
import onestep.id.sinergiin.R;

public class AdminQcAdapter extends RecyclerView.Adapter<AdminQcAdapter.ViewHolder> {
    private Activity activity;
    private List<mAdminQc> list;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public AdminQcAdapter(Activity activity, List<mAdminQc> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_admin_qc,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final mAdminQc m = list.get(position);
        holder.txtTitle.setText(m.getTitle());
        holder.txtDate.setText(m.getDate());
        holder.img.setImageUrl(m.getImg(),imageLoader);
        holder.verif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Verif", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDate;
        NetworkImageView img;
        Button verif;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.tv_produk);
            txtDate = (TextView) itemView.findViewById(R.id.tv_pengrajin);
            img = (NetworkImageView) itemView.findViewById(R.id.iv_produk);
            verif = (Button) itemView.findViewById(R.id.iv_verif);
        }
    }
}
