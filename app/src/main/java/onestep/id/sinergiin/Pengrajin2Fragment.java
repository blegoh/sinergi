package onestep.id.sinergiin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.PengrajinEcomAdapter;
import onestep.id.sinergiin.Model.mPengrajinEcom;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengrajin2Fragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<mPengrajinEcom> list = new ArrayList<>();

    public Pengrajin2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengrajin2, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclePengrajinEcom);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        list.add(new mPengrajinEcom("1","10.000","Kayu",""));
        list.add(new mPengrajinEcom("2","20.000","Bambu",""));
        adapter = new PengrajinEcomAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
