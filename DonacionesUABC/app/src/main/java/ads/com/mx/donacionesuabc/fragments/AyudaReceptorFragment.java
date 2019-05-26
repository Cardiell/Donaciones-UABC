package ads.com.mx.donacionesuabc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import ads.com.mx.donacionesuabc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AyudaReceptorFragment extends Fragment {

    private int cont = 0;
    private int[] imagenes = {R.drawable.donacion2, R.drawable.donacion2_1, R.drawable.donacion2_2,R.drawable.electronica}; // Poner las nuevas imagenes de ayuda Receptor

    public AyudaReceptorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        View v = inflater.inflate(R.layout.fragment_ayuda_receptor, container, false);


        next = v.findViewById(R.id.next);
        prev = v.findViewById(R.id.prev);
        imagen = v.findViewById(R.id.imagenAyuda);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(imagenes.length);
                if (cont < (imagenes.length - 1))
                    imagen.setImageResource(imagenes[++cont]);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont > 0)
                    imagen.setImageResource(imagenes[--cont]);
            }
        });
        return v;
    }


    private ImageButton next;
    private ImageButton prev;
    private ImageView imagen;
}
