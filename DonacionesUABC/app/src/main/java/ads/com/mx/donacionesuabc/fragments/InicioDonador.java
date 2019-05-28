package ads.com.mx.donacionesuabc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.activities.RegistroArticuloActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioDonador extends Fragment implements View.OnClickListener {

    Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inicio_donador, container, false);
        ImageButton btnRopa = v.findViewById(R.id.btnRopa);
        ImageButton btnUtiles = v.findViewById(R.id.btnUtiles);
        ImageButton btnElectronica = v.findViewById(R.id.btnElectronica);
        ImageButton btnOtros = v.findViewById(R.id.btnOtros);

        btnRopa.setOnClickListener(this);
        btnUtiles.setOnClickListener(this);
        btnElectronica.setOnClickListener(this);
         btnOtros.setOnClickListener(this);


        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRopa:
                intent = new Intent(getActivity(), RegistroArticuloActivity.class);
                startActivity(intent);
                break;
            case R.id.btnElectronica:
                intent = new Intent(getActivity(),RegistroArticuloActivity.class);
                startActivity(intent);
                break;
            case R.id.btnOtros:
                intent = new Intent(getActivity(),RegistroArticuloActivity.class);
                startActivity(intent);
                break;
            case R.id.btnUtiles:
                intent = new Intent(getActivity(),RegistroArticuloActivity.class);
                startActivity(intent);
                break;
        }
    }
}
