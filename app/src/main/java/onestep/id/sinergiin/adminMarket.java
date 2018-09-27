package onestep.id.sinergiin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import onestep.id.sinergiin.Adapter.AdminMarketAdpater;
import onestep.id.sinergiin.Model.mAdminMarket;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminMarket extends Fragment {
    private ListView listView;
    private AdminMarketAdpater adapter;
    private List<mAdminMarket> list = new ArrayList<>();

    public adminMarket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_market, container, false);
        listView = (ListView) view.findViewById(R.id.lv_adminMarket);
        list.add(new mAdminMarket("1","","Lampu Bambu","3500","Produk ini blablabla"));
        list.add(new mAdminMarket("2","","Kerajinan Bambu","3000","Produk ini blablabla"));
        adapter = new AdminMarketAdpater(getActivity(),list);
        listView.setAdapter(adapter);
        return view;
    }

}
