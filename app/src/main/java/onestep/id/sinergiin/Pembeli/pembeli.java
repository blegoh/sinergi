package onestep.id.sinergiin.Pembeli;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import onestep.id.sinergiin.BottomNavigationHelper;
import onestep.id.sinergiin.R;

public class pembeli extends AppCompatActivity {
    RelativeLayout fragment;
    android.support.v4.app.Fragment ecom, pesanan, pembelian ,profile;

    private BottomNavigationView.OnNavigationItemSelectedListener listener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Bundle data = new Bundle();
            android.support.v4.app.Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_commerce:
                    fragment = ecom;
                    if (fragment == null) {
                        ecom = new pembeliCommerce();
                    }
                    break;
                case R.id.nav_pesanan:
                    fragment = pesanan;
                    if (fragment == null) {
                        pesanan = new pembeliPesanan();
                    }
                    break;
                case R.id.nav_pembelian:
                    fragment = pembelian;
                    if (fragment == null){
                        pembelian = new pembeliPembelian();
                    }
                    break;
                case R.id.nav_profil:
                    fragment = profile;
                    if (fragment == null) {
                        profile = new pembeliProfile();
                    }
                    break;
            }
            if (fragment != null)
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
            return fragment != null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli);
        fragment = (RelativeLayout) findViewById(R.id.fragment);

        pembeliCommerce pembeliCommerce = new pembeliCommerce();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, pembeliCommerce);
        fragmentTransaction.commit();

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(listener);
        BottomNavigationHelper.disableShiftMode(navigationView);
    }
}
