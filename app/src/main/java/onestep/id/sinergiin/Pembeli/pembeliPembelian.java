package onestep.id.sinergiin.Pembeli;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.PembeliPembelianAdapter;
import onestep.id.sinergiin.Model.mPembeliPembelian;
import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class pembeliPembelian extends Fragment {
    private ListView listView;
    private List<mPembeliPembelian> list = new ArrayList<>();
    private PembeliPembelianAdapter adapter;

    public pembeliPembelian() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pembeli_pembelian, container, false);
        listView = (ListView) view.findViewById(R.id.lv_pembeliPembelian);
        list.add(new mPembeliPembelian("1","http://209.97.170.199:8000/api/sinergiin/gambar/05102018103303.jpg","jam kayu","Penjual : Sofyan","30","20 Oktober 2018"));
        list.add(new mPembeliPembelian("2","http://209.97.170.199:8000/api/sinergiin/gambar/03102018124225.jpg","tutup lampu","Penjual : Nizar","20","25 Oktober 2018"));
        adapter = new PembeliPembelianAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        return view;
    }

}
