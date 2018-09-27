package onestep.id.sinergiin.Admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminOthers extends Fragment {


    public adminOthers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_others, container, false);
        return view;
    }

}
