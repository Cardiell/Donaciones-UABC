package ads.com.mx.donacionesuabc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import java.util.List;

import ads.com.mx.donacionesuabc.ListaCompartir;
import ads.com.mx.donacionesuabc.R;
import ads.com.mx.donacionesuabc.activities.DonadorActivity;
import ads.com.mx.donacionesuabc.dao.ArticuloDAO;
import ads.com.mx.donacionesuabc.entidades.Articulo;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompartirFragment extends Fragment {

    private int index;
    private ListaCompartir lista;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    public CompartirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        View v = inflater.inflate(R.layout.fragment_compartir, container, false);
        List<Articulo> arti= new ArticuloDAO().listarxUsuario(DonadorActivity.user.getIdUsuario());
        lista = new ListaCompartir(this,arti);
        Button btn = v.findViewById(R.id.btnCompartir);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Su informacion ha sido compartirda \n\t\ten su perfil de Facebook :)",Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = v.findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(lista);
        lista.filter("");
        return v;
    }


    public int getIndex(){
        return this.index;
    }

    public void setIndex(int i){
        this.index = i;
    }
}
