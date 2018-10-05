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

import onestep.id.sinergiin.Adapter.PengrajinProduksiAdapter;
import onestep.id.sinergiin.Model.mPengrajinProduksi;
import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengrajin4Fragment extends Fragment {
    private ListView listView;
    private PengrajinProduksiAdapter adapter;
    private List<mPengrajinProduksi> list = new ArrayList<>();

    public Pengrajin4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengrajin4, container, false);
        listView = (ListView) view.findViewById(R.id.lv_PengrajinPesanan);
        list.add(new mPengrajinProduksi("1","","Brian","Keranjang Rotan","300","20 Oktober 2018","dikirim"));
        list.add(new mPengrajinProduksi("2","","Eldianto","Gelang rotan","40", "30 Oktober 2018","dikerjakan"));
        list.add(new mPengrajinProduksi("3","","Tono Supriadi","Gelang Anyam","10", "10 Oktober 2018","diterima"));
        list.add(new mPengrajinProduksi("4","","Tika Lestari","Gantungan Kunci Rotan","10", "11 November 2018","diterima"));
        adapter = new PengrajinProduksiAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPengrajinProduksi m = list.get(position);
                Intent i = new Intent(getActivity(),pengrajinDetailPesanan.class);
                i.putExtra("status",m.getStatus());
                startActivity(i);
            }
        });
        return view;
    }

}
