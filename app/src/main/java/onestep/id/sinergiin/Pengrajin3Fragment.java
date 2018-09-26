package onestep.id.sinergiin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.PengrajinQcAdapter;
import onestep.id.sinergiin.Model.mPengrajinQc;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengrajin3Fragment extends Fragment {
    private ListView listView;
    private PengrajinQcAdapter adapter;
    private List<mPengrajinQc> list = new ArrayList<>();

    public Pengrajin3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengrajin3, container, false);
        listView = (ListView) view.findViewById(R.id.listPengrajinQc);
        list.add(new mPengrajinQc("1", "", "Lampu Bambu", "5 Oktober 2018", R.drawable.ic_check_circle_black_24dp));
        list.add(new mPengrajinQc("2", "", "Kerajinan Bambu", "10 Oktober", R.drawable.ic_check_circle_grey_24dp));
        adapter = new PengrajinQcAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        return view;
    }

}
