package onestep.id.sinergiin.Pembeli;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import onestep.id.sinergiin.PembeliCart;
import onestep.id.sinergiin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class pembeliProfile extends Fragment {
    Button cart;

    public pembeliProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pembeli_profile, container, false);
        cart = (Button) view.findViewById(R.id.btnCart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),PembeliCart.class);
                startActivity(i);
            }
        });
        return view;
    }

}
