package onestep.id.sinergiin.Pengrajin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import onestep.id.sinergiin.TinyDB;
import onestep.id.sinergiin.login;
import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengrajin6Fragment extends Fragment {
    Button btnLogout;
    TinyDB tinyDB;

    public Pengrajin6Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pengrajin6, container, false);
        btnLogout = view.findViewById(R.id.btn_logout);
        tinyDB = new TinyDB(getActivity());
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), login.class);
                tinyDB.clear();
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        return view;
    }

}
