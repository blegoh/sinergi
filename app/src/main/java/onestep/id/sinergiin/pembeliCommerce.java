package onestep.id.sinergiin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.PembeliEcomAdapter;
import onestep.id.sinergiin.Model.mPembeliEcom;


/**
 * A simple {@link Fragment} subclass.
 */
public class pembeliCommerce extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<mPembeliEcom> list = new ArrayList<>();

    public pembeliCommerce() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pembeli_commerce, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_pembeliCommmerce);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        list.add(new mPembeliEcom("1", "10.000", "Kayu", ""));
        list.add(new mPembeliEcom("2", "20.000", "Bambu", ""));
        adapter = new PembeliEcomAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
