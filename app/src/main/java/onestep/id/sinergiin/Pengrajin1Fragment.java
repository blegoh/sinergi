package onestep.id.sinergiin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.PengrajinMarketAdapter;
import onestep.id.sinergiin.Model.mPengrajinMarket;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengrajin1Fragment extends Fragment {
    private ListView listView;
    private PengrajinMarketAdapter adapter;
    private List<mPengrajinMarket> list = new ArrayList<>();

    public Pengrajin1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengrajin1, container, false);
        listView = (ListView) view.findViewById(R.id.listPengrajinMarket);
        list.add(new mPengrajinMarket("1","","Lampu Bambu","3500","Produk ini blablabla"));
        list.add(new mPengrajinMarket("2","","Kerajinan Bambu","3000","Produk ini blablabla"));
        adapter = new PengrajinMarketAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        return view;
    }

}
