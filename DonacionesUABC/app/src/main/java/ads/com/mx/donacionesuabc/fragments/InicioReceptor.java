package ads.com.mx.donacionesuabc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ads.com.mx.donacionesuabc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioReceptor extends Fragment {


    public InicioReceptor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio_receptor, container, false);
    }

}
