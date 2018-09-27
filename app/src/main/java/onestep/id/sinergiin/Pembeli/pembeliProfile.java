package onestep.id.sinergiin.Pembeli;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class pembeliProfile extends Fragment {


    public pembeliProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pembeli_profile, container, false);
    }

}
