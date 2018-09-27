package onestep.id.sinergiin.Admin;


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

import onestep.id.sinergiin.Adapter.AdminPembagianAdapter;
import onestep.id.sinergiin.Model.mAdminPembagian;
import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminPembagian extends Fragment {
    private ListView listView;
    private AdminPembagianAdapter adapter;
    private List<mAdminPembagian> list = new ArrayList<>();

    public adminPembagian() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_pembagian, container, false);
        listView = (ListView) view.findViewById(R.id.lv_adminPembagian);
        list.add(new mAdminPembagian("1","Abbi","Lampu Bambu","300","5 Oktober 2018",""));
        list.add(new mAdminPembagian("2","Umroh","Kerajinan Kayu","350","10 Oktober 2018",""));
        adapter = new AdminPembagianAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdminPembagian m = list.get(position);
                Intent i = new Intent(getActivity(),adminPembagianDetail.class);
                startActivity(i);
            }
        });
        return view;
    }

}
