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

import onestep.id.sinergiin.Adapter.AdminQcAdapter;
import onestep.id.sinergiin.Model.mAdminQc;
import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminQuality extends Fragment {
    private ListView listView;
    private AdminQcAdapter adapter;
    private List<mAdminQc> list = new ArrayList<>();


    public adminQuality() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_quality, container, false);
        listView = (ListView) view.findViewById(R.id.rv_adminQc);
        list.add(new mAdminQc("1", "", "Kayu", "5 Oktober 2018"));
        list.add(new mAdminQc("2", "", "Bambu", "10 Oktober 2018"));
        adapter = new AdminQcAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdminQc m = list.get(position);
                Intent i = new Intent(getActivity(),adminDetailQc.class);
                startActivity(i);
            }
        });
        return view;
    }

}
