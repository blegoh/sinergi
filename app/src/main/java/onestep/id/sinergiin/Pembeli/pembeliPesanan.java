package onestep.id.sinergiin.Pembeli;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.PembeliPesananAdapter;
import onestep.id.sinergiin.Model.mPembeliPesanan;
import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class pembeliPesanan extends Fragment {
    private ListView listView;
    private PembeliPesananAdapter adapter;
    private List<mPembeliPesanan> list = new ArrayList<>();
    private FloatingActionButton fab;

    public pembeliPesanan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pembeli_pesanan, container, false);
        listView = (ListView) view.findViewById(R.id.lv_pembeliPesanan);
        list.add(new mPembeliPesanan("1","Lampu Bambu","100","3","Verifikasi"));
        list.add(new mPembeliPesanan("2","Kursi Kayu","200","5","Belum Verifikasi"));
        adapter = new PembeliPesananAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        fab = (FloatingActionButton) view.findViewById(R.id.fabPesanan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),pembeliPesananDetail.class);
                startActivity(i);
            }
        });
        return view;
    }

}
