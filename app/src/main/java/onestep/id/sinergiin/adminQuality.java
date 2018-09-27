package onestep.id.sinergiin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.AdminQcAdapter;
import onestep.id.sinergiin.Model.mAdminQc;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminQuality extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<mAdminQc> list = new ArrayList<>();


    public adminQuality() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_quality, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_adminQc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.add(new mAdminQc("1","","Kayu","5 Oktober 2018"));
        list.add(new mAdminQc("2","","Bambu","10 Oktober 2018"));
        adapter = new AdminQcAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
