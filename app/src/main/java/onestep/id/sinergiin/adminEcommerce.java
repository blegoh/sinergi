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

import onestep.id.sinergiin.Adapter.AdminEcomAdapter;
import onestep.id.sinergiin.Model.mAdminEcom;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminEcommerce extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<mAdminEcom> list = new ArrayList<>();

    public adminEcommerce() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_ecommerce, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_adminCommerce);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.add(new mAdminEcom("1","","Kayu","10.000"));
        list.add(new mAdminEcom("2","","Bambu","20.000"));
        adapter = new AdminEcomAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
