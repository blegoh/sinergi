package onestep.id.sinergiin.Pengrajin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.PengrajinPembelianAdapter;
import onestep.id.sinergiin.Model.mPengrajinPembelian;
import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengrajin5Fragment extends Fragment {
    private ListView listView;
    private PengrajinPembelianAdapter adapter;
    private List<mPengrajinPembelian> list = new ArrayList<>();

    public Pengrajin5Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengrajin5, container, false);
        listView = (ListView) view.findViewById(R.id.lv_Pengrajinpembelian);
        list.add(new mPengrajinPembelian("1","","Brian","Kayu","300","20 Oktober 2018"));
        list.add(new mPengrajinPembelian("2","","Eldi","Bambu","200", "30 Oktober 2018"));
        list.add(new mPengrajinPembelian("3","","Sofyan","Lampu","200", "30 Oktober 2018"));
        adapter = new PengrajinPembelianAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPengrajinPembelian m = list.get(position);
                Intent i = new Intent(getActivity(),pengrajinDetailPembelian.class);
                startActivity(i);
            }
        });
        return view;
    }

}
